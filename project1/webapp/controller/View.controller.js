sap.ui.define([
    "project1/controller/BaseController",
    "sap/ui/model/json/JSONModel",
    "sap/ui/core/mvc/Controller",
    'sap/m/MessageBox'
], function (BaseController, JSONModel, MessageBox) {
    "use strict";


    return BaseController.extend("project1.controller.View", {
        onInit: function () {
            this.oOwnerComponent = this.getOwnerComponent();
            this.oRouter = this.oOwnerComponent.getRouter();
            this.oModel = this.oOwnerComponent.getModel();

            // Create a model for username and password
            var oLoginData = {
                name: "",
                user_id: "",
                password: "",
                passwordVisible: false
            };

            // Create JSON model and set it to the view
            var oLoginModel = new JSONModel(oLoginData);
            this.getView().setModel(oLoginModel, "loginMdl");
        },
onPressLogin: function () {
    var oUsername = this.getView().byId("username").getValue();
    var oPassword = this.getView().byId("password").getValue();

    console.log("Username:", oUsername);
    console.log("Password:", oPassword);

    $.ajax({
        url: "/api/login",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            username: oUsername,
            password: oPassword
        }),
        success: function () {
            sap.m.MessageBox.success("Login successful!", {
                title: "Welcome",
                onClose: function () {
                    var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
                    oRouter.navTo("Home");
                }.bind(this)
            });
        },
        error: function () {
            sap.m.MessageBox.error("Invalid username or password. Please try again.", {
                title: "Login Failed",
                actions: [sap.m.MessageBox.Action.OK]
            });
        }
    });
}


    });
});
