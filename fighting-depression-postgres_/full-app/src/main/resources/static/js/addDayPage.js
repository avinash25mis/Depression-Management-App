var idList=[];


    function readURL(input) {
        if (input.files && input.files[0]) {
          var isUploaded= uploadData()
            if(isUploaded){
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#attachedImage')
                        .attr('src', e.target.result)
                        .width(150)
                        .height(200);
                };
    
                reader.readAsDataURL(input.files[0]);

            }
        
           
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
              data: fd,
              contentType: false,
              processData: false,
              async:false,

            success: function (data) {
            alert("file uploaded")
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
$(document).ready(function() {

$("#clearIt").click(function(){

    $("#file").val("");
   /* $("#file").replaceWith( fileControl.val('').clone( true ) );*/

});







 $("#saveDay").click(function(){
       $("#docList").val(idList);

        var dayData = $('#saveDayForm');

          if(dayData.length > 0 ){


           $.ajax({
              url: "/addData/addDayData",
              type: "POST",
              data: dayData.serialize(),

        success: function (data) {
          alert("Day saved");
          idList=[];
        },
       error: function (jqXHR) {
        alert(jqXHR.responseJSON.message);

        },

    });
			  }


    });

});











