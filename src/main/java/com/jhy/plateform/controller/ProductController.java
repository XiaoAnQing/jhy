package com.jhy.plateform.controller;

import com.jhy.plateform.domain.Attach;
import com.jhy.plateform.domain.Customer;
import com.jhy.plateform.domain.ProductType;
import com.jhy.plateform.query.CustomerQuery;
import com.jhy.plateform.query.ProductTypeQuery;
import com.jhy.plateform.service.CustomerService;
import com.jhy.plateform.service.ProductTypeService;
import com.jhy.plateform.utils.JsonModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.Product;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.ProductQuery;
import com.jhy.plateform.service.ProductService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}