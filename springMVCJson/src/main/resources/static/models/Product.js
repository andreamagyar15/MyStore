function Product(){
  this.id="";
  this.title="";
  this.description="";
  this.size="";
  this.amount="";
  this.price="";
  this.imageURL="";
  this.arrival="";
  this.type="";
  this.imgfile="";
}
Product.prototype.getProductDetails=function(id){
  var that=this;
  return $.ajax({
       url:"http://localhost:8090/products"+"/"+id,
       method:'GET'
   })
   .then (function(response){
       that.title=response.title;
       that.description=response.description;
       that.size=response.size;
       that.price=response.price;
       that.image=response.image;
       that.arrival=response.arrival;
       that.amount=response.amount;
	   
   });
 }
  Product.prototype.createNewProduct=function(newElement,token){
       return $.ajax({
           url:"http://localhost:8090/secured/products",
           //xhrFields: {
      //  withCredentials: true
  //  },
           method:"POST",
           //contentType: 'application/json',
           credentials: "same-origin",
           data:{
               "title":newElement.title,
               "description": newElement.description,
               "price" :newElement.price,
               "amount":newElement.amount,
               "size": newElement.size,
               "arrival":newElement.arrival,
               "prodType":newElement.type,
			   "imageName":newElement.imageName,
			   "imageInput":newElement.imageInput,
			   
           },
           beforeSend : function( xhr ) {
         xhr.setRequestHeader("X-Auth-Token", token );

     },
            // Access-Control-Allow-Credentials: true,
             //withCredentials: false
           //},
       })
}
Product.prototype.uploadImage=function(imgName,imageFile,token){
	 return $.ajax({
           url:"http://localhost:8090/secured/productImage",
           method:"POST",
           credentials: "same-origin",
           data:{"imageFileBinary":imageFile,
					"imageName":imgName},
           beforeSend : function( xhr ) {
         xhr.setRequestHeader("X-Auth-Token", token );

     },
            
       })
}
Product.prototype.deleteProduct=function(type,id,token){
  return $.ajax({
       url:"http://localhost:8090/secured/products/delete/"+type+"/"+id,
       method:'DELETE',

       headers: {
               'X-Auth-Token': token
          },
          beforeSend : function( xhr ) {
            xhr.setRequestHeader("X-Auth-Token", token );
    },
   });
};
Product.prototype.updateProduct=function(id,newproduct,token){
  return $.ajax({
      url:"http://localhost:8090/secured/products/update/"+id,
      method:"POST",
      headers: {
              'X-Auth-Token': token
         },
         credentials: "same-origin",
      data:{
          "title":newproduct.title,
          "description": newproduct.description,
          "price" :newproduct.price,
          "amount":newproduct.amount,
          "size": newproduct.size,
          "arrival":newproduct.arrival,
          "prodType":newproduct.prodType
      },
      beforeSend : function( xhr ) {
    xhr.setRequestHeader("X-Auth-Token", token );
},
      //success:function(response){
      //    console.log("response post: ",response);
     // }//
  })
}
