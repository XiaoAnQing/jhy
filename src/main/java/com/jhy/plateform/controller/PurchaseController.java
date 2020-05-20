package com.jhy.plateform.controller;

import com.github.pagehelper.PageInfo;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.domain.*;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.PurchaseQuery;
import com.jhy.plateform.service.PurchaseService;
import com.jhy.plateform.service.SupplierService;
import com.jhy.plateform.utils.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/purchases")
@ControllerAnno(addUI = "/purchase/save", detailUI = "/purchase/detail", editUI = "/purchase/save", listUI = "/purchase/list")
public class PurchaseController{

	@Autowired
	PurchaseService purchaseService;

	@Autowired
	SupplierService supplierService;

	/**
	 * 分页查询[返回JSON]
	 * @param purchaseQuery
	 * @param request
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value="", method= RequestMethod.GET, headers="X-Requested-With=XMLHttpRequest")
	public @ResponseBody
	Map<String,Object> list(PurchaseQuery purchaseQuery, HttpServletRequest request) throws KPException{
		Map<String,Object> result;
		result = new HashMap<String,Object>();
		PageInfo<Purchase> results =null;
		results = purchaseService.findBySelective(purchaseQuery);
		result.put("success", true);
		result.put("msg","查询采购成功");
		result.put("total", results.getTotal());
		result.put("data", results.getList());
		return result;
	}

	@RequestMapping(value="", method=RequestMethod.GET)
	public String list(HttpServletRequest request,ModelMap modelMap) throws KPException {
		return "/purchase/list";
	}

	/**
	 * 获取审核通过的采购订单
	 * @param purchaseQuery
	 * @param request
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value="pass", method= RequestMethod.GET, headers="X-Requested-With=XMLHttpRequest")
	public @ResponseBody Map<String,Object> listPass(PurchaseQuery purchaseQuery, HttpServletRequest request) throws KPException{
		Map<String,Object> result= new HashMap<String,Object>();
		purchaseQuery.setStatus(StatusUtil.STATUS_PURCHASE_PASSED);
		PageInfo<Purchase> results = purchaseService.findBySelective(purchaseQuery);

		result.put("success", true);
		result.put("msg","查询采购订单成功");
		result.put("total", results.getTotal());
		result.put("data", results.getList());
		return result;
	}




	/**
	 * 添加界面
	 * @param modelMap
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value={"/new"}, method={RequestMethod.GET})
	public String addUI(ModelMap modelMap) throws KPException {
		modelMap.addAttribute(new Purchase());
		return "/purchase/save";
	}


	/**
	 * 添加
	 * @param t
	 * @param modelMap
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value="/",method = { RequestMethod.POST })
	public String add(Purchase t,ModelMap modelMap) throws KPException {
		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
		User user = (User) request.getSession().getAttribute(ConstantUtil.SESSION_KEY);
		t.setNum(StringUtil.formateDate(DateUtil.getCurrentDate(),"yyyyMMddHHmmss")+user.getId());
		//设置跟单信息
		t.setUserId(user.getId());
		t.setUserName(user.getName());
		purchaseService.add(t);
		return "redirect:/purchases";
	}

	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value={"/{ids}"}, method={RequestMethod.DELETE},headers="X-Requested-With=XMLHttpRequest")
	public @ResponseBody
	JsonModel delete(@PathVariable("ids") String ids){
		JsonModel jsonModel = new JsonModel();
		try {
			String id[] = ids.split(",");
			int result;
			if (id.length > 1) {
				// 批量删除
				result = this.purchaseService.deleteByIds(id);
			} else {
				//单个删除
				result = this.purchaseService.deleteById(ids);
			}
			if(result==0){
				jsonModel.setSuccess(false);
				jsonModel.setMsg("删除失败");
			}else{
				jsonModel.setSuccess(true);
				jsonModel.setMsg("成功删除" + result + "条记录");
			}
		} catch (Exception e) {
			jsonModel.setSuccess(false);
			jsonModel.setMsg("删除采购失败,请重新刷新数据再删除");
		}
		return jsonModel;
	}


	//创建Excel
	@RequestMapping("/createExcel")
	public String createExcel(HttpServletResponse response) throws IOException {

		//创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wb = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=wb.createSheet("订单报表");
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1=sheet.createRow(0);
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell=row1.createCell(0);
		//设置单元格内容
		cell.setCellValue("订单报表");
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
		//在sheet里创建第二行
		HSSFRow row2=sheet.createRow(1);
		//创建单元格并设置单元格内容
//		row2.createCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
		row2.createCell(0).setCellValue("编号");
		row2.createCell(1).setCellValue("下单时间");
		row2.createCell(2).setCellValue("交货日期");
		row2.createCell(3).setCellValue("供应商");
		row2.createCell(4).setCellValue("总金额");
		row2.createCell(5).setCellValue("支付信息");
		row2.createCell(6).setCellValue("凭据图");
		row2.createCell(7).setCellValue("跟单");
		row2.createCell(8).setCellValue("状态");
		row2.createCell(9).setCellValue("备注");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//在sheet里创建内容
		List<Purchase> purchases = purchaseService.findAll();
		for(int i=0;i<purchases.size();i++){
			HSSFRow row =sheet.createRow(2+i);
			row.createCell(0).setCellValue(purchases.get(i).getNum());
			row.createCell(1).setCellValue(sdf.format(purchases.get(i).getCreateDate()));
			row.createCell(2).setCellValue(sdf.format(purchases.get(i).getEndDate()));
			Supplier supplier = supplierService.findById(purchases.get(i).getSupplierId()+"");
			row.createCell(3).setCellValue(supplier.getName());
			row.createCell(4).setCellValue(purchases.get(i).getPrice()+"");
			row.createCell(5).setCellValue(purchases.get(i).getPayInfo());
			row.createCell(6).setCellValue(purchases.get(i).getEvidenceImg());
			row.createCell(7).setCellValue(purchases.get(i).getUserName());
			row.createCell(8).setCellValue(purchases.get(i).getStatus());
			row.createCell(9).setCellValue(purchases.get(i).getRemark());
		}

		//输出Excel文件
		OutputStream output=response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename="+StringUtil.toUtf8String("采购订单报表.xls"));
		/*response.setHeader("Content-disposition", "attachment; filename=订单报表.xls");*/
		response.setContentType("application/msexcel");
		wb.write(output);
		output.close();
		return null;
	}
}