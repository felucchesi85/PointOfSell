(function() {
    'use strict';

    angular.module('sampleApp').controller('RelatedProductsController', RelatedProductsController);

    RelatedProductsController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','modalService','dataService','$state','RestrictedCharacter.Types'];

    function RelatedProductsController($scope, $rootScope, device ,GlobalVariable,DialogFactory,modalService,dataService,$state,restrictCharacter)
    {
        $scope.prodRel =[];
        $scope.addRelatedProductsGrid = [];
        $scope.closePopup = function()
        {
            DialogFactory.close(true);
        };
        $scope.addRelatedProducts = function()
        {
            for(var i=0;i<$scope.prodRel.length;i++)
            {
                if($scope.realtedProd == $scope.prodRel[i].description) {
                    $scope.addRelatedProductsGrid.push({
                        "productId": $scope.prodRel[i].productId,
                        "productNo": $scope.prodRel[i].productNo,
                        "oldProductNo": $scope.prodRel[i].oldProductNo,
                        "categoryId": $scope.prodRel[i].categoryId,
                        "vendorId": $scope.prodRel[i].vendorId,
                        "brandId": $scope.prodRel[i].brandId,
                        "altNo": $scope.prodRel[i].altNo,
                        "description": $scope.prodRel[i].description,
                        "costPrice": $scope.prodRel[i].costPrice,
                        "markup": $scope.prodRel[i].markup,
                        "retailPrice": $scope.prodRel[i].retailPrice,
                        "quantity": $scope.prodRel[i].quantity,
                        "minProductQuantity": $scope.prodRel[i].minProductQuantity,
                        "returnRule": $scope.prodRel[i].returnRule,
                        "image": $scope.prodRel[i].image,
                        "createdDate": $scope.prodRel[i].createdDate,
                        "categoryName": $scope.prodRel[i].categoryName,
                        "brandName": $scope.prodRel[i].brandName,
                        "vendorName": $scope.prodRel[i].vendorName,
                        "imeiNo": $scope.prodRel[i].imeiNo,
                        "addTax": $scope.prodRel[i].addTax,
                        "stock": $scope.prodRel[i].stock,
                        "quantityForSell": $scope.prodRel[i].quantityForSell,
                        "relatedProduct": $scope.prodRel[i].relatedProduct
                    });

                }
            }
        };
        function getCtaegoryData()
        {
            for(var i=0;i<GlobalVariable.getProducts.length;i++)
            {
                if(GlobalVariable.getProducts[i].categoryName == 'Related Product')
                {
                    $scope.prodRel.push( {
                        "productId": GlobalVariable.getProducts[i].productId,
                        "productNo": GlobalVariable.getProducts[i].productNo,
                        "oldProductNo": GlobalVariable.getProducts[i].oldProductNo,
                        "categoryId": GlobalVariable.getProducts[i].categoryId,
                        "vendorId": GlobalVariable.getProducts[i].vendorId,
                        "brandId": GlobalVariable.getProducts[i].brandId,
                        "altNo": GlobalVariable.getProducts[i].altNo,
                        "description": GlobalVariable.getProducts[i].description,
                        "costPrice": GlobalVariable.getProducts[i].costPrice,
                        "markup": GlobalVariable.getProducts[i].markup,
                        "retailPrice": GlobalVariable.getProducts[i].retailPrice,
                        "quantity": GlobalVariable.getProducts[i].quantity,
                        "minProductQuantity": GlobalVariable.getProducts[i].minProductQuantity,
                        "returnRule": GlobalVariable.getProducts[i].returnRule,
                        "image": GlobalVariable.getProducts[i].image,
                        "createdDate": GlobalVariable.getProducts[i].createdDate,
                        "categoryName": GlobalVariable.getProducts[i].categoryName,
                        "brandName": GlobalVariable.getProducts[i].brandName,
                        "vendorName": GlobalVariable.getProducts[i].vendorName,
                        "imeiNo": GlobalVariable.getProducts[i].imeiNo,
                        "addTax": GlobalVariable.getProducts[i].addTax,
                        "stock": GlobalVariable.getProducts[i].stock,
                        "quantityForSell": GlobalVariable.getProducts[i].quantityForSell,
                        "relatedProduct": GlobalVariable.getProducts[i].relatedProduct
                    });
                }
            }
            getRelatedProducts();
        }
        function render()
        {
            getCtaegoryData();

        }
        function getRelatedProducts()
        {
            var url=" http://localhost:8080/getRelatedProduct?productNo="+GlobalVariable.mainProductNo;
            dataService.Get(url,onGetRelatedSuccess,onGetRelatedError,'application/json','application/json');
        }
        function onGetRelatedSuccess(response)
        {
            if(response.length !== 0)
            $scope.addRelatedProductsGrid = response;
        }
        function onGetRelatedError(response)
        {

        }
        $scope.updateRelatedProducts = function()
        {
            var request = [];
          var url=" http://localhost:8080/addRelatedProduct";
            for(var i=0;i<$scope.addRelatedProductsGrid.length;i++)
            {
                request.push({
                    "productNo":GlobalVariable.mainProductNo,
                    "relatedProductNo":$scope.addRelatedProductsGrid[i].productNo
                });
            }
            dataService.Post(url,request,onAddRelatedSuccess,onAddRelatedError,'application/json','application/json');
        };
        function onAddRelatedSuccess(response)
        {
                DialogFactory.close(true);
        }
        function onAddRelatedError(response)
        {

        }
        render();
    }

})();