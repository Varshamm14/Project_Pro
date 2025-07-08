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

        // Event handler for the Sign In button press
        onPressLogin: function () {
            var oModel = this.getView().getModel("loginMdl");
            var sUsername = oModel.getProperty("/user_id");
            var sPassword = oModel.getProperty("/password");

            if (!sUsername || !sPassword) {
                MessageBox.error("Please enter both username and password.");
                return;
            }

            // Call Spring Boot backend API
            fetch("/api/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    userId: sUsername,
                    password: sPassword
                })
            })
                .then(response => {
                    if (response.ok) {
                        return response.text(); // Expecting plain success message
                    } else {
                        throw new Error("Invalid credentials");
                    }
                })
                .then(message => {
                    MessageBox.success(message);
                    this.oRouter.navTo("product");
                })
                .catch(error => {
                    MessageBox.error(error.message);
                });
        }
    });
});
