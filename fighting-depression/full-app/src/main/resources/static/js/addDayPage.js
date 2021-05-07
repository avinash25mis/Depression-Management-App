var idList=[];
 var div=$("#addDayPageId");

    function readURL(input) {
  $("#loaderDiv").show();
        if (input.files && input.files[0]) {
             uploadData()
             var reader = new FileReader();
              reader.onload = function (e) {
                 $('#attachedImage').attr('src', e.target.result);
                };
              reader.readAsDataURL(input.files[0]);
         }

 }


   


function uploadData(){

    var uploaded=false;
        var fd = new FormData();
        var files = $('#file')[0].files;

        // Check file selected or not
        if(files.length > 0 ){
           fd.append('file',files[0]);

           $.ajax({
              url: '/addData/upload',
              type: 'POST',
            headers:{
            "Authorization": "Bearer " + authToken
             },
              data: fd,
              contentType: false,
              processData: false,
              beforeSend: function () {
                $("#loaderDiv").show();
              },

            success: function (data) {
              $("#loaderDiv").hide();
            $("#currentFileId").val(data)
            $("#idList").val(data);
            idList.push(data);
            uploaded= true;
        },
         error: function (jqXHR) {
          
        alert(jqXHR.responseJSON.message);

        },

  });
  }

return uploaded;
}



function saveDayData(){
    var hours= $("#hours").val();
    var minutes= $("#minutes").val();
    var time=hours+":"+minutes;
   $("#time").val(time);
     $('#saveDayForm').validate({
          errorClass: "my-error-class",
          validClass: "my-valid-class"
         });

       $("#docList").val(idList);

        var dayData = $('#saveDayForm');

        if(!dayData.valid()){

          return;
         }

          if(dayData.length > 0 ){
        startAppLoader(div);

           $.ajax({
              url: "/addData/addDayData",
              type: "POST",
            headers:{
            "Authorization": "Bearer " + authToken
             },
              data: dayData.serialize(),

        success: function (data) {
        stopAppLoader(div)
          $.notify("Day Data Saved", "success");
          idList=[];
        },
       error: function (jqXHR) {
        alert(jqXHR.responseJSON.message);

        },

    });
 }

}








$(document).ready(function() {

 fetchImage(7);

 var retrivedTime =$("#time").val()
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