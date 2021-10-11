const hostName=window.location.origin;
var appContextPath="";
var apiUrl="";



   function getHomePage(){
           startAppLoader();
                $.ajax({
                   url: apiUrl+"/homePage",
                   type: "GET",
                   headers:{
                      "Authorization": "Bearer " + window.sessionStorage.getItem("authTokenId")
                           },

         success: function (jqXHR) {
                stopAppLoader();
                $("#addDayPageId").html("");
               $("#viewDayPageId").html("");
               $("#dashboardPageId").html("");
               $("#subscriberPageId").html("");
               $("#homePageDiv").show();
               $("#homePageNav").show();

             },
            error: function (jqXHR) {
             alert(jqXHR.responseJSON.message);

             },

          });

         }




   function getAddDayPage(id){
           startAppLoader();
                $.ajax({
                   url: apiUrl+"/addDayPage",
                   type: "POST",
                   headers:{
                               "Authorization": "Bearer " + window.sessionStorage.getItem("authTokenId")
                           },
                   data: { "id": id} ,

            success: function (jqXHR) {
                stopAppLoader();
                $("#addDayPageId").html(jqXHR);
               $("#viewDayPageId").html("");
               $("#dashboardPageId").html("");
               $("#subscriberPageId").html("");
                $("#homePageDiv").hide();
                $("#homePageNav").hide();

             },
            error: function (jqXHR) {
             alert(jqXHR.responseJSON.message);

             },

          });

         }



        function getViewDayPage(){
        startAppLoader();
           $.ajax({
              url: apiUrl+"/viewDayPage",
              type: "POST",
                 headers:{
            "Authorization": "Bearer " + window.sessionStorage.getItem("authTokenId")
             },

       success: function (jqXHR) {
         stopAppLoader();
          $("#addDayPageId").html("");
          $("#subscriberPageId").html("");
          $("#viewDayPageId").html(jqXHR);
          $("#dashboardPageId").html("");
           $("#homePageDiv").hide();
           $("#homePageNav").hide();

        },
       error: function (jqXHR) {
        alert(jqXHR.responseJSON.message);

        },

     });

    }



     function getDashBoardPage(){
       startAppLoader();
           $.ajax({
              url: apiUrl+"/dashBoardPage",
              type: "POST",
             headers:{
            "Authorization": "Bearer " + window.sessionStorage.getItem("authTokenId")
             },
       success: function (jqXHR) {
          stopAppLoader();
           $("#addDayPageId").html("");
          $("#viewDayPageId").html("");
          $("#subscriberPageId").html("");
           $("#dashboardPageId").html(jqXHR);
            $("#homePageDiv").hide();
            $("#homePageNav").hide();
        },
       error: function (jqXHR) {
        alert(jqXHR.responseJSON.message);

        },

     });

    }




    function getAllSubscribers(){
           startAppLoader();
               $.ajax({
                  url: apiUrl+"/Subscriber/page",
                  type: "GET",
                 headers:{
                "Authorization": "Bearer " + window.sessionStorage.getItem("authTokenId")
                 },
           success: function (jqXHR) {
              stopAppLoader();
               $("#subscriberPageId").html(jqXHR);
               $("#addDayPageId").html("");
              $("#viewDayPageId").html("");
               $("#dashboardPageId").html("");
               $("#viewDayPageId").css("display","none");
                $("#homePageDiv").hide();
                $("#homePageNav").hide();
            },
           error: function (jqXHR) {
            alert(jqXHR.responseJSON.message);

            },

         });

        }




         function deleteDayById(dayId){
           startAppLoader();
               $.ajax({
                  url:apiUrl+ "/delete/"+dayId,
                  type: "POST",
                 headers:{
                "Authorization": "Bearer " + window.sessionStorage.getItem("authTokenId")
                 },
           success: function (jqXHR) {
            stopAppLoader();
          $("#addDayPageId").html("");
          $("#viewDayPageId").html(jqXHR);
          $("#dashboardPageId").html("");
           $("#subscriberPageId").html("");
            $.notify("Day Data Deleted", "success");
            },
           error: function (jqXHR) {
            alert(jqXHR.responseJSON.message);

            },

         });

        }


   function fetchImage(id){

          startAppLoader();
               $.ajax({
                  url: apiUrl+"/addData/getFile/"+id,
                  type: "GET",
                 headers:{
                 "Authorization": "Bearer " + window.sessionStorage.getItem("authTokenId")
             },
           success: function (jqXHR) {
               stopAppLoader();

               $("#attachedImage").attr("src","data:image/JPEG;base64,"+jqXHR).width(200).height(200);


            },
           error: function (jqXHR) {
            alert(jqXHR.responseJSON.message);

            },

         });

        }


    function showModal(id){
    $("#modalConfirmButton").show();
    $( "#viewDayPageId" ).foggy(true);
    $( "#exampleModalCenter" ).addClass( "in" );
     $("#exampleModalCenter").css("display","block");
     if(id!=undefined || id!=null){
     $("#fieldOfModal").val(id);
     }
    }

   function hideModal(){
    $( "#viewDayPageId" ).foggy(false);
    $( "#exampleModalCenter" ).removeClass("in" );
    $("#exampleModalCenter").css("display","none");
    $("#imageOfModal").html("");
     $("#fieldOfModal").html("");
     $("#alertOfModal").html("");
    }

   function confirmDecision(){
   var id=$("#fieldOfModal").val();
    if(id!=undefined && id!=null){
      deleteDay(id);
     }
    $( "#viewDayPageId" ).foggy(false);
    $( "#exampleModalCenter" ).removeClass("in");
    $("#exampleModalCenter").css("display","none");
    $("#imageOfModal").html("");
     $("#fieldOfModal").html("");
     $("#alertOfModal").html("");
    }


    function startAppLoader(divSelector){
    if(divSelector!=undefined){
     divSelector.foggy(true);
     }
      $("#loaderDiv").show()
    }


    function stopAppLoader(divSelector){
     if(divSelector!=undefined){
     divSelector.foggy(false);
     }
     $("#loaderDiv").hide();
    }


    function logOut(){
    window.sessionStorage.removeItem("authTokenId");
     window.location="";


    }


$( document ).ready(function() {
appContextPath = $("#contextPathId").val();
apiUrl=hostName+appContextPath
if($("#authTokenId").val()!=undefined){
   var authToken=$("#authTokenId").val();
   window.sessionStorage.setItem("authTokenId", authToken);

   }




     $(".navbar a, footer a[href='#myPage']").on('click', function(event) {

      // Make sure this.hash has a value before overriding default behavior
     if (this.hash !== "") {

       // Prevent default anchor click behavior
       event.preventDefault();

       // Store hash
       var hash = this.hash;

       // Using jQuery's animate() method to add smooth page scroll
       // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
       $('html, body').animate({
         scrollTop: $(hash).offset().top
       }, 900, function(){

         // Add hash (#) to URL when done scrolling (default click behavior)
         window.location.hash = hash;
         });
       } // End if
     });


});


    function readURL(input) {
      if (input.files && input.files[0]) {
             var reader = new FileReader();
              reader.onload = function (e) {
                 $('#attachedImage').attr('src', e.target.result);
                };
              reader.readAsDataURL(input.files[0]);
         }

 }


 function clearImage(){

      $("#file").val("");

     $('#attachedImage').attr('src', "#");
     $("#fileIdDashBoard").val("");
     idList="";

  }


