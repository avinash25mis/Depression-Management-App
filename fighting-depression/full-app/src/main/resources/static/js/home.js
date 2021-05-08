var authToken="";



   function getHomePage(){
           startAppLoader();
                $.ajax({
                   url: "/homePage",
                   type: "GET",
                   headers:{
                      "Authorization": "Bearer " + authToken
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
                   url: "/addDayPage",
                   type: "POST",
                   headers:{
                               "Authorization": "Bearer " + authToken
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
              url: "/viewDayPage",
              type: "POST",
                 headers:{
            "Authorization": "Bearer " + authToken
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
              url: "/dashBoardPage",
              type: "POST",
             headers:{
            "Authorization": "Bearer " + authToken
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
                  url: "/delete/"+dayId,
                  type: "POST",
                 headers:{
                "Authorization": "Bearer " + authToken
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
                  url: "/addData/getFile/"+id,
                  type: "GET",
                 headers:{
                 "Authorization": "Bearer " + authToken
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


$( document ).ready(function() {
if($("#authTokenId").val()!=undefined){
   authToken=$("#authTokenId").val();
   }
});
