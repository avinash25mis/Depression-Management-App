var div=$("#dashboardPageId");
function dashBoardSaveFunction(){


         var formData=$("#dashboardFormId").serialize();
           $.ajax({
              url: apiUrl+"/dashboard/update",
              type: "POST",
            headers:{
            "Authorization": "Bearer " + window.sessionStorage.getItem("authTokenId")
             },
             data: formData,

       success: function (jqXHR) {
        stopAppLoader(div);
         $.notify("Day Data Saved", "success");
        },
       error: function (jqXHR) {
       stopAppLoader(div);
        alert(jqXHR.responseJSON.message);

        },

    });

}




function uploadData(){
  startAppLoader(div);
    var uploaded=false;
        var fd = new FormData();
        var files = $('#file')[0].files;

        // Check file selected or not
        if(files.length > 0 ){
           fd.append('file',files[0]);

           $.ajax({
              url: apiUrl+'/addData/upload',
              type: 'POST',
            headers:{
            "Authorization": "Bearer " + window.sessionStorage.getItem("authTokenId")
             },
              data: fd,
              contentType: false,
              processData: false,

            success: function (data) {
            $("#fileIdDashBoard").val(data)
             uploaded= true;
            dashBoardSaveFunction();

        },
         error: function (jqXHR) {

        alert(jqXHR.responseJSON.message);

        },

  });
  }else{
  dashBoardSaveFunction();
  }

return uploaded;
}





 $(document).ready(function() {
 var fileIdDashBoard=$("#fileIdDashBoard").val();
 if(fileIdDashBoard!=undefined && fileIdDashBoard!=""){
    fetchImage(fileIdDashBoard);
  }

  });