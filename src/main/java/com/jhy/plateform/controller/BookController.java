package com.jhy.plateform.controller;

import com.github.pagehelper.PageInfo;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.domain.Book;
import com.jhy.plateform.domain.BookCount;
import com.jhy.plateform.domain.Customer;
import com.jhy.plateform.domain.User;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.BookQuery;
import com.jhy.plateform.service.BookService;
import com.jhy.plateform.service.CustomerService;
import com.jhy.plateform.utils.ConstantUtil;
import com.jhy.plateform.utils.DateUtil;
import com.jhy.plateform.utils.JsonModel;
import com.jhy.plateform.utils.StringUtil;
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
import java.util.*;

@Controller
@RequestMapping("/books")
@ControllerAnno(addUI = "/book/save", detailUI = "/book/detail", editUI = "/book/save", listUI = "/book/list")
public class BookController{

	@Autowired
	BookService bookService;

	@Autowired
	CustomerService customerService;


	/**
	 * 分页查询[返回JSON]
	 * @param bookQuery
	 * @param request
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value="", method= RequestMethod.GET, headers="X-Requested-With=XMLHttpRequest")
	public @ResponseBody
	Map<String,Object> list(BookQuery bookQuery, HttpServletRequest request) throws KPException{
		Map<String,Object> result;
		result = new HashMap<String,Object>();
		PageInfo<Book> results =null;
		results = bookService.findBySelective(bookQuery);
		result.put("success", true);
		result.put("msg","查询订单成功");
		result.put("total", results.getTotal());
		result.put("data", results.getList());
		return result;
	}


	@RequestMapping(value="", method=RequestMethod.GET)
    public String list(HttpServletRequest request,ModelMap modelMap) throws KPException {
		return "/book/list";
	}



	/**
	 * 添加界面
	 * @param modelMap
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value={"/new"}, method={RequestMethod.GET})
	public String addUI(ModelMap modelMap) throws KPException {
		modelMap.addAttribute(new Book());
		return "/book/save";
	}


	/**
	 * 添加
	 * @param t
	 * @param modelMap
	 * @return
	 * @throws KPException
	 */
	@RequestMapping(value="/",method = { RequestMethod.POST })
	public String add(Book t,ModelMap modelMap) throws KPException {
		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
		User user = (User) request.getSession().getAttribute(ConstantUtil.SESSION_KEY);
		t.setNum(StringUtil.formateDate(DateUtil.getCurrentDate(),"yyyyMMddHHmmss")+user.getId());
		//设置跟单信息
		t.setUserId(user.getId());
		bookService.add(t);
		return "redirect:/books";
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
				result = this.bookService.deleteByIds(id);
			} else {
				//单个删除
				result = this.bookService.deleteById(ids);
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
			jsonModel.setMsg("删除订单失败,请重新刷新数据再删除");
		}
		return jsonModel;
	}

	/**
	 * 订单总量查询
	 */
	@ResponseBody
	@RequestMapping("bookCount")
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
		row2.createCell(0).setCellValue("客户订单号");
		row2.createCell(1).setCellValue("订单号");
		row2.createCell(2).setCellValue("下单时间");
		row2.createCell(3).setCellValue("交货日期");
		row2.createCell(4).setCellValue("总金额");
		row2.createCell(5).setCellValue("客户");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//在sheet里创建内容
		List<Book> books = bookService.findAll();
		for(int i=0;i<books.size();i++){
			HSSFRow row =sheet.createRow(2+i);
			row.createCell(0).setCellValue(books.get(i).getCustomerNum());
			row.createCell(1).setCellValue(books.get(i).getNum());
			row.createCell(2).setCellValue(sdf.format(books.get(i).getCreateDate()));
			row.createCell(3).setCellValue(sdf.format(books.get(i).getEndDate()));
			row.createCell(4).setCellValue(books.get(i).getPrice()+"");
			Customer customer = customerService.findById(books.get(i).getCustomerId()+"");
			row.createCell(5).setCellValue(customer.getName());
		}

		//输出Excel文件
		OutputStream output=response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename="+StringUtil.toUtf8String("订单报表.xls"));
		/*response.setHeader("Content-disposition", "attachment; filename=订单报表.xls");*/
		response.setContentType("application/msexcel");
		wb.write(output);
		output.close();
		return null;
	}
}