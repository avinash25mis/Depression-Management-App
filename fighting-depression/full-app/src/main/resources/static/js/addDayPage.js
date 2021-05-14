var idList=[];
 var div=$("#addDayPageId");

    function readURL(input) {
      if (input.files && input.files[0]) {
             var reader = new FileReader();
              reader.onload = function (e) {
                 $('#attachedImage').attr('src', e.target.result);
                };
              reader.readAsDataURL(input.files[0]);
         }

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
            $("#currentDocId").val(data)
             idList=[];
             idList.push(data);
            uploaded= true;
            callingSave();

        },
         error: function (jqXHR) {
          
        alert(jqXHR.responseJSON.message);

        },

  });
  }else{
  callingSave();
  }

return uploaded;
}



function saveDayData(){

    var hours= $("#hours").val();
    var minutes= $("#minutes").val();
    var time=hours+":"+minutes;
   $("#time").val(time);
    var dayData = $('#saveDayForm');
     dayData.validate({
          errorClass: "my-error-class",
          validClass: "my-valid-class"
         });

     if(dayData.length < 0 || !dayData.valid()){

          return;
        }

         var uploaded= uploadData();

}


function callingSave(){
$("#docList").val(idList);
           $.ajax({
              url: apiUrl+"/addData/addDayData",
              type: "POST",
            headers:{
            "Authorization": "Bearer " + window.sessionStorage.getItem("authTokenId")
             },
              data: $('#saveDayForm').serialize(),

        success: function (data) {
        stopAppLoader(div);
         $("#file").val("");
          $.notify("Day Data Saved", "success");
          $("#dayId").val(data);

        },
       error: function (jqXHR) {
         stopAppLoader(div);
         alert(jqXHR.responseJSON.message);

        },

    });


}






$(document).ready(function() {
var currentDocId=$("#currentDocId").val();
if(currentDocId!=undefined && currentDocId!=""){
   idList.push(currentDocId);
 fetchImage(currentDocId);
 }

 var retrivedTime =$("#time").val();
 if(retrivedTime!=""){
 var res = retrivedTime.split(":");
 $("#hours").val(res[0]);
 $("#minutes").val(res[1]);
 }




 document.querySelectorAll('input[type=number]')
   .forEach(e => e.oninput = () => {
     // Always 2 digits
     if (e.value.length >= 2) e.value = e.value.slice(0, 2);
     // 0 on the left (doesn't work on FF)
     if (e.value.length === 1) e.value = '0' + e.value;
     // Avoiding letters on FF
     if (!e.value) e.value = '00';
   });




$("#clearIt").click(function(){

    $("#file").val("");
   $('#attachedImage').attr('src', "#");

   idList="";

});

});