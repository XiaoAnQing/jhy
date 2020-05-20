package com.jhy.plateform.controller;

import com.jhy.plateform.domain.DeliveryItem;
import com.jhy.plateform.exception.ExceptionKind;
import com.jhy.plateform.service.DeliveryItemService;
import com.jhy.plateform.utils.JsonModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jhy.plateform.anno.ControllerAnno;
import com.jhy.plateform.controller.base.BaseController;
import com.jhy.plateform.domain.Store;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.StoreQuery;
import com.jhy.plateform.service.StoreService;

@Controller
@RequestMapping("/stores")
@ControllerAnno(addUI = "/store/save", detailUI = "/store/detail", editUI = "/store/save", listUI = "/store/list")
public class StoreController extends BaseController<Store,StoreQuery>{
	
	@Autowired
	public void setStoreService(StoreService storeService) {
		this.baseService = storeService;
	}

	@Autowired
    StoreService storeService;

	@Autowired
	DeliveryItemService deliveryItemService;

	@Override
	public Store beforeSave(ModelMap modelMap, Store t) throws KPException {
		return t;
	}

	@Override
	public void beforeSaveUI(ModelMap modelMap, String id) throws KPException {
	}

	@Override
	protected void beforeDelete(String[] ids) throws KPException {
	}

	@RequestMapping(value="{storeId}/unbind",method= RequestMethod.PUT)
	@ResponseBody
	public JsonModel unbind(@PathVariable("storeId") Integer storeId) {
		Store store = baseService.findById(storeId+"");
		if(store==null || store.getMaterialId()==null){
			throw  new KPException(ExceptionKind.PARAM_E);
		}
		boolean result = ((StoreService)baseService).unbind(storeId);
		JsonModel jsonModel =  new JsonModel();
		jsonModel.setSuccess(result);
		jsonModel.setMsg(result?"解绑成功":"解绑失败");
		return jsonModel;
	}

	//ontextPath+"store/"+storeId+"/bind/"+materialId,"PUT","",t
	@RequestMapping(value="{storeId}/bind/{materialId}",method= RequestMethod.PUT)
	@ResponseBody
	public JsonModel bind(@PathVariable("storeId") Integer storeId,@PathVariable("materialId") Integer materialId) {
		Store store = baseService.findById(storeId+"");
		if(store==null || store.getMaterialId()!=null){
			throw  new KPException(ExceptionKind.PARAM_E);
		}
		boolean result = ((StoreService)baseService).bind(storeId,materialId);
		JsonModel jsonModel =  new JsonModel();
		jsonModel.setSuccess(result);
		jsonModel.setMsg(result?"绑定成功":"绑定失败");
		return jsonModel;
	}

	@RequestMapping(value = "/insertStroe",method = RequestMethod.GET)
	@ResponseBody
	public JsonModel insertStore(String [] id,String [] materialCount,Integer deliveryItemId){
		JsonModel jsonModel = new JsonModel();

	    Store store = new Store();
		int result = 0;
		for(int i=0;i<id.length;i++){
			if(Integer.parseInt(materialCount[i])!=0){
				int temp = Integer.parseInt(materialCount[i]);
				//仓库增加
				store.setId(Integer.parseInt(id[i]));
				store.setMaterialCount(storeService.findById(id[i]).getMaterialCount()+temp);
				result = storeService.updateById(store);

				//为入库材料删减
				DeliveryItem deliveryItem = deliveryItemService.findById(deliveryItemId+"");
				deliveryItem.setLeftCount(deliveryItem.getLeftCount()-temp);
				deliveryItemService.updateById(deliveryItem);
			}
		}
		jsonModel.setSuccess(result==1?true:false);
		jsonModel.setMsg(result==1?"入库成功":"入库失败");
		return  jsonModel;
	}

}