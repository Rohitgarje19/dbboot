sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/m/MessageBox",
    "jquery.sap.global",
    "rohit/util/service"
], function(Controller, MessageBox, jQuery, service) {
    return Controller.extend("rohit.controller.Main", {
		onInit: function(){
			var oModel = new sap.ui.model.json.JSONModel();
			oModel.setData({
				"postPayLoad" :{
					"companyName":"",
					"firstName":"",
					"lastName":"",
					"website":"",
					"email":"",
					"status":"A",
					"gstNo":""
				},
				"vendor":{}
			});   // HAL format has _embedded
			this.getView().setModel(oModel);
		},
		onDelete: function(){
			var that = this;     
		    var oTable = this.getView().byId("idTable");
		    var iIndex = oTable.getSelectedIndex();

		    if(iIndex === -1){
		        MessageBox.warning("Please select a record to delete.");
		        return;
		    }

		    var oModel = this.getView().getModel();
		    var oVendor = oModel.getProperty("/vendor/" + iIndex);
			console.log("oVendor:", JSON.stringify(oVendor)); 
			var sHref = oVendor._links.self.href;
			var iId = sHref.substring(sHref.lastIndexOf("/") + 1);

		    var that = this;
		    MessageBox.confirm("Delete vendor: " + oVendor.companyName + "?", {
		        onClose: function(sAction){
		            if(sAction === MessageBox.Action.OK){
		                service.callService("/vendor/" + iId, "DELETE", {})
		                .then(function(){
		                    MessageBox.success("Deleted successfully!");
		                    // Remove from model locally
		                    var aVendors = oModel.getProperty("/vendor");
		                    aVendors.splice(iIndex, 1);
		                    oModel.setProperty("/vendor", aVendors);
		                    oTable.clearSelection();
		                })
		                .catch(function(err){
		                    console.log(err);
		                    MessageBox.error("Delete failed!");
		                });
		            }
		        }
		    });
		},
		onSave: function(){
			var oModel = this.getView().getModel();
			var payload = oModel.getProperty("/postPayLoad");
			service.callService("/vendor","POST",payload).then(function(){
				MessageBox.confirm("post success");
			}).catch(function(){
				MessageBox.error("post failed");
			});
		},
		onLoadData: function(){
			//alert("todo:microservice");
			var that = this;
			service.callService("/newVendor","GET",{}).then(function(data){
			    var oTable = that.getView().byId("idTable");
				var oModel = that.getView().getModel(); 
				
			    oModel.setProperty("/vendor", data._embedded.vendor);   // HAL format has _embedded
			    that.getView().setModel(oModel);
			    oTable.bindRows("/vendor");        // matches collectionResourceRel
			}).catch(function(err){
			    console.log(err);
			});
		}
    });
});