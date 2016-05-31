package com.hengsu.uliketu.nav.controller;

import com.hengsu.uliketu.core.annotation.IgnoreAuth;
import com.hengsu.uliketu.core.annotation.Permission;
import com.hengsu.uliketu.core.model.AuthModel;
import com.hengsu.uliketu.core.vo.ReturnCode;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.uliketu.nav.service.NavLinkService;
import com.hengsu.uliketu.nav.model.NavLinkModel;
import com.hengsu.uliketu.nav.vo.NavLinkVO;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestApiController
@RequestMapping("/uliketu")
public class NavLinkRestApiController {

    private final Logger logger = LoggerFactory.getLogger(NavLinkRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private NavLinkService navLinkService;


    /**
     * 点击链接
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/nav/clickink/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<String>> clickNavLink(@PathVariable Long id,
                                                                 @Value("#{request.getAttribute('userId')}") Long userId) {
        navLinkService.clickLink(id, userId);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 导航链接详情
     *
     * @param id
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/nav/navLink/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<NavLinkModel>> getNavLinkById(@PathVariable Long id) {
        NavLinkModel navLinkModel = navLinkService.findByPrimaryKey(id);
        ResponseEnvelope<NavLinkModel> responseEnv = new ResponseEnvelope<>(navLinkModel, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 导航链接列表
     *
     * @param columnId
     * @param pageable
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/nav/{columnId}/navLinks", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<NavLinkModel>>> listNavLinks(@PathVariable Long columnId,
                                                                             Pageable pageable) {
        NavLinkModel param = new NavLinkModel();
        param.setColumnId(columnId);
        List<NavLinkModel> navLinkModes = navLinkService.selectPage(param, pageable);
        long count = navLinkService.selectCount(param);
        Page<NavLinkModel> page = new PageImpl<>(navLinkModes, pageable, count);
        ResponseEnvelope<Page<NavLinkModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 添加导航链接
     *
     * @param navLinkVO
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/nav/navLink", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Integer>> createNavLink(@RequestBody NavLinkVO navLinkVO) {
        NavLinkModel navLinkModel = beanMapper.map(navLinkVO, NavLinkModel.class);
        navLinkModel.setAddTime(new Date());
        Integer result = navLinkService.createSelective(navLinkModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 删除导航链接
     *
     * @param id
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/nav/navLink/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<Integer>> deleteNavLinkByPrimaryKey(@PathVariable Long id) {
        Integer result = navLinkService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 更新导航链接
     *
     * @param id
     * @param navLinkVO
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN, AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/nav/navLink/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<Integer>> updateNavLinkByPrimaryKeySelective(@PathVariable Long id,
                                                                                        @RequestBody NavLinkVO navLinkVO) {
        NavLinkModel navLinkModel = beanMapper.map(navLinkVO, NavLinkModel.class);
        navLinkModel.setId(id);
        Integer result = navLinkService.updateByPrimaryKeySelective(navLinkModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 导航统计报表
     * @return
     */
    @Permission(roles = {AuthModel.ROLE_ADMIN,AuthModel.ROLE_SUPER_ADMIN})
    @RequestMapping(value = "/admin/nav/export", method = RequestMethod.GET)
    public void exportReport(HttpServletResponse response) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("提现信息");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("序列号");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("月份");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("赠送元宝总数");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("获得元宝会员总数");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("已经支付金额");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("支付失败金额");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("备注");
        cell.setCellStyle(style);

        row = sheet.createRow(1);

        Date endTime= DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
        Date startTime= DateUtils.addMonths(endTime,-1);

        Map map = null;
        row.createCell(0).setCellValue("0");
        row.createCell(1).setCellValue(new SimpleDateFormat("yyyy-MM").format(startTime));
        Double apply = (Double)map.get("apply");
        Double success = (Double)map.get("success");
        Double failure = (Double)map.get("failure");
        row.createCell(2).setCellValue(apply+success+failure);
        row.createCell(3).setCellValue(apply);
        row.createCell(4).setCellValue(success);
        row.createCell(5).setCellValue(failure);
        row.createCell(6).setCellValue("");
        String fileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        try{
            response.setHeader("content-disposition",
                    "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + ".xls");
            response.setContentType("application/vnd.ms-excel");
            OutputStream fout = response.getOutputStream();
            wb.write(fout);
            fout.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
