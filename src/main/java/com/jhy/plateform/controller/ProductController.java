package com.jhy.plateform.controller;

import com.jhy.plateform.domain.*;
import com.jhy.plateform.query.CustomerQuery;
import com.jhy.plateform.query.ProductTypeQuery;
import com.jhy.plateform.service.CustomerService;
import com.jhy.plateform.service.ProductTypeService;
import com.jhy.plateform.utils.JsonModel;
import com.jhy.plateform.utils.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.ProductQuery;
import com.jhy.plateform.service.ProductService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/products")
@ControllerAnno(addUI = "/product/save", detailUI = "/product/detail", editUI = "/product/save", listUI = "/product/list")
public class ProductController extends BaseController<Product,ProductQuery>{

	@Autowired
	ProductTypeService productTypeService;

	@Autowired
	CustomerService customerService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.baseService = productService;
	}

	@Override
	public Product beforeSave(ModelMap modelMap, Product t) throws KPException {
		return t;
	}

	@Override
	public void beforeSaveUI(ModelMap modelMap, String id) throws KPException {
		//查找所有商品类别
		ProductTypeQuery productTypeQuery = new ProductTypeQuery();
		List<ProductType> productTypes =  productTypeService.findBySelective(productTypeQuery).getList();
		modelMap.put("productTypes",productTypes);
		//查找所有客户
		CustomerQuery customerQuery = new CustomerQuery();
		List<Customer> customers =  customerService.findBySelective(customerQuery).getList();
		modelMap.put("customers",customers);
	}

	@Override
	protected void beforeDelete(String[] ids) throws KPException {
	}


	/**
	 * 根据商品Id查找其配件信息
	 * @param productId
	 * @return
	 */
	@RequestMapping(value="{productId}/attachs",method= RequestMethod.GET)
	@ResponseBody
	public JsonModel attachInfo(@PathVariable("productId") Integer productId){
		JsonModel jsonModel = new JsonModel();
		List<Attach> attaches = ((ProductService)baseService).findAttach(productId);
		jsonModel.setSuccess(true);
		jsonModel.setMsg("查找配件成功");
		jsonModel.setData(attaches);
		return jsonModel;
	}
	/**
	 * 转发到配件界面
	 * @param id
	 * @return
	 */
	@RequestMapping(value="{id}/attach",method= RequestMethod.GET)
	public String attach(@PathVariable("id") Integer id,ModelMap modelMap){
		modelMap.addAttribute("productId",id);
		return "/product/attach";
	}

	/**
	 * 商品和材料绑定
	 * @param id
	 * @return
	 */
	@RequestMapping(value="{id}/attach",method= RequestMethod.PUT)
	@ResponseBody
	public JsonModel attach(@PathVariable("id") Integer id,Product product){
		JsonModel jsonModel = new JsonModel();
		List<Attach> attaches = product.getAttachs();
		if(attaches==null || attaches.isEmpty()){
			jsonModel.setSuccess(false);
			jsonModel.setMsg("参数错误");
			return jsonModel;
		}

		ProductService productService = (ProductService) baseService;
		jsonModel.setSuccess(productService.bindAttach(id,product.getAttachs()));
        jsonModel.setMsg(jsonModel.isSuccess()?"绑定成功":"绑定失败");
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
		row2.createCell(0).setCellValue("名字");
		row2.createCell(1).setCellValue("简介");
		row2.createCell(2).setCellValue("产品设计图");
		row2.createCell(3).setCellValue("客户编号");
		row2.createCell(4).setCellValue("产品编号");
		row2.createCell(5).setCellValue("历史单价");
		row2.createCell(6).setCellValue("单价");
		row2.createCell(7).setCellValue("脾套");
		row2.createCell(8).setCellValue("镜片");
		row2.createCell(9).setCellValue("电镀色");
		row2.createCell(10).setCellValue("材料");
		row2.createCell(11).setCellValue("商品类别");
		row2.createCell(12).setCellValue("客户");
		row2.createCell(13).setCellValue("跟单");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//在sheet里创建内容
		List<Product> products = productService.findAll();
		for(int i=0;i<products.size();i++){
			HSSFRow row =sheet.createRow(2+i);
			row.createCell(0).setCellValue(products.get(i).getName());
			row.createCell(1).setCellValue(products.get(i).getTitle());
			row.createCell(2).setCellValue(products.get(i).getDetailImg());
			row.createCell(3).setCellValue(products.get(i).getCustomerId());
			row.createCell(4).setCellValue(products.get(i).getNum());
			row.createCell(5).setCellValue(products.get(i).getOriPrice()+"");
			row.createCell(6).setCellValue(products.get(i).getPrice()+"");
			row.createCell(7).setCellValue(products.get(i).getSplenicCuff());
			row.createCell(8).setCellValue(products.get(i).getGlasses());
			row.createCell(9).setCellValue(products.get(i).getElectroplatingColour() );
			row.createCell(10).setCellValue(products.get(i).getTypeName() );
			row.createCell(11).setCellValue(products.get(i).getProductTypeId());
			row.createCell(12).setCellValue(products.get(i).getCustomerId());
			row.createCell(13).setCellValue(products.get(i).getUserId()+"");
		}

		//输出Excel文件
		OutputStream output=response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename="+ StringUtil.toUtf8String("产品报表.xls"));
		/*response.setHeader("Content-disposition", "attachment; filename=订单报表.xls");*/
		response.setContentType("application/msexcel");
		wb.write(output);
		output.close();
		return null;
	}


	/**
	 * 订单总量查询
	 */
	/*@ResponseBody
	@RequestMapping("productCount")
	public  JsonModel bookCount (){
		JsonModel jsonModel = new JsonModel();
		BookCount bookCount = new BookCount();
		int [] counts = new int[12];
		String [] months = new String[12];

		Date date = new Date();

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year= c.get(Calendar.YEAR);

		int month = c.get(Calendar.MONTH);;

		bookCount.setMonth(month);
		bookCount.setYear(year);

		for(int i=0;i<12;i++){
			int count = bookService.count(bookCount);
			counts[12-i-1] = count;
			months[12-i-1] = month-- + "月";

			if(month==0){
				month=12;
				year--;
				bookCount.setYear(year);
			}

			bookCount.setMonth(month);
		}

		bookCount.setCounts(counts);
		bookCount.setMonths(months);

		jsonModel.setData(bookCount);
		return  jsonModel;
	}*/
}