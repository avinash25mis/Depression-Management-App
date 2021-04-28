var authToken="abcd";


  function loginForm(){

         var formData=$("#loginFormId").serialize();
           $.ajax({
              url: "/login",
              type: "POST",
             data: formData,

       success: function (jqXHR) {
          $("#loginDivId").html("");
          $("#addDayPageId").html("");
          $("#viewDayPageId").html("");
          $("#dashboardPageId").html(jqXHR);
        },
       error: function (jqXHR) {
        alert(jqXHR.responseJSON.message);

        },

     });

    }



       function getAddDayPage(id){

           $.ajax({
              url: "/addDayPage",
              type: "POST",


       success: function (jqXHR) {
           $("#loginDivId").html("");
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

           $.ajax({
              url: "/viewDayPage",
              type: "POST",

           contentType: 'application/json',
       success: function (jqXHR) {
           $("#loginDivId").html("");
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

           $.ajax({
              url: "/dashBoardPage",
              type: "POST",

           contentType: 'application/json',
       success: function (jqXHR) {
           $("#loginDivId").html("");
          $("#addDayPageId").html("");
          $("#viewDayPageId").html("");
          $("#dashboardPageId").html(jqXHR);
        },
       error: function (jqXHR) {
        alert(jqXHR.responseJSON.message);

        },

     });

    }