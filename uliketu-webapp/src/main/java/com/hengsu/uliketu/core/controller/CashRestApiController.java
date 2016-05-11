package com.hengsu.uliketu.core.controller;

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
import org.springframework.web.bind.annotation.*;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.uliketu.core.service.CashService;
import com.hengsu.uliketu.core.model.CashModel;
import com.hengsu.uliketu.core.vo.CashVO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestApiController
@RequestMapping("/uliketu")
public class CashRestApiController {

    private final Logger logger = LoggerFactory.getLogger(CashRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CashService cashService;

    /**
     * 查询单个提现
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/cash/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<CashVO>> getCashById(@PathVariable Long id) {
        CashModel cashModel = cashService.findByPrimaryKey(id);
        CashVO cashVO = beanMapper.map(cashModel, CashVO.class);
        ResponseEnvelope<CashVO> responseEnv = new ResponseEnvelope<>(cashVO, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 我的提现记录
     *
     * @param userId
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/cashs", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<CashModel>>> getCashs(@Value("#{request.getAttribute('userId')}") Long userId,
                                                                      Pageable pageable) {
        CashModel param = new CashModel();
        param.setUserid(userId);
        List<CashModel> cashModels = cashService.selectPage(param, pageable);
        long count = cashService.selectCount(param);
        Page<CashModel> page = new PageImpl<>(cashModels, pageable, count);
        ResponseEnvelope<Page<CashModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 提交提现申请
     *
     * @param cashVO
     * @return
     */
    @RequestMapping(value = "/cash", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<CashModel>> addCash(@Value("#{request.getAttribute('userId')}") Long userId,
                                                               @RequestBody CashVO cashVO) {
        CashModel cashModel = beanMapper.map(cashVO, CashModel.class);
        cashModel.setUserid(userId);
        cashModel = cashService.addCash(cashModel);
        ResponseEnvelope<CashModel> responseEnv = new ResponseEnvelope<>(cashModel, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 根据状态查提现列表
     *
     * @param status
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/admin/cashs", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<CashModel>>> getCashsByStatus(@RequestParam int status,
                                                                              Pageable pageable) {
        CashModel param = new CashModel();
        param.setStatus(status);
        List<CashModel> cashModels = cashService.selectPage(param, pageable);
        long count = cashService.selectCount(param);
        Page<CashModel> page = new PageImpl<>(cashModels, pageable, count);
        ResponseEnvelope<Page<CashModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 同意提现申请
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/agreecash/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<String>> agreeCash(@PathVariable Long id) {
        cashService.agreeCash(id);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 拒绝提现申请
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/refusecash/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<String>> refuseCash(@PathVariable Long id) {
        cashService.refuseCash(id);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    @RequestMapping(value = "/admin/cash/export", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<String>> exportReport() {
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
        cell.setCellValue("提现总金额");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("申请总金额");
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

        Map map = cashService.selectGroupByStatus(startTime,endTime);
        row.createCell(0).setCellValue("0");
        row.createCell(1).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(startTime));
        Double apply = (Double)map.get("apply");
        Double success = (Double)map.get("success");
        Double failure = (Double)map.get("failure");
        row.createCell(2).setCellValue(apply+success+failure);
        row.createCell(3).setCellValue(apply);
        row.createCell(4).setCellValue(success);
        row.createCell(5).setCellValue(failure);
        row.createCell(6).setCellValue("");


        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}
