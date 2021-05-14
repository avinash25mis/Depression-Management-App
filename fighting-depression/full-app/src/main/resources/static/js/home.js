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
          $("#viewDayPageId").html(jqXHR);
          $("#dashboardPageId").html("");

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
           $("#dashboardPageId").html(jqXHR);
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

               $("#attachedImage").attr("src","data:image/JPEG;base64,"+jqXHR).width(150).height(150);


            },
           error: function (jqXHR) {
            alert(jqXHR.responseJSON.message);

            },

         });

        }


    function showModal(id){
    $("#modalConfirmButton").show();
    $( "#viewDayPageId" ).foggy(true);
    $( "#exampleModal" ).addClass( "show" );
     $("#exampleModal").css("display","block");
     if(id!=undefined || id!=null){
     $("#fieldOfModal").val(id);
     }
    }

   function hideModal(){
    $( "#viewDayPageId" ).foggy(false);
    $( "#exampleModal" ).removeClass("show" );
    $("#exampleModal").css("display","none");
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
    $( "#exampleModal" ).removeClass("show");
    $("#exampleModal").css("display","none");
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
});
