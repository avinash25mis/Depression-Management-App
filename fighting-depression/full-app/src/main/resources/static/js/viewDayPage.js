 var div=$("#viewDayPageId")
 startAppLoader(div);
$(document).ready(function() {
        var data;

        $.ajax({
              url:  apiUrl+"/viewDayPage/data",
              type: "POST",
             headers:{
            "Authorization": "Bearer " + window.sessionStorage.getItem("authTokenId")
             },
              dataType: "json",
            contentType: "application/json",
            async:false,

        success: function (d) {
         stopAppLoader(div);
         data=d;

        },
       error: function (jqXHR) {
        alert(jqXHR.responseJSON.message);

        },

    });

        $('#example').DataTable({
        "processing": false,
        "serverSide": false,
          "scrollY":       true,
          "scrollX":        true,
       "scrollCollapse": true,
       "fixedColumns": true,
         "data":  data,



       "columns": [
              {
               data: "id",
              "render": function( data, type, full, meta) {
                data = '<a  class="glyphicon glyphicon-pencil"  href="#"  onclick="editDayData('+data+')"></a>'
               return data;
               }

             },
             {
               data: "id",
               "render": function( data, type, full, meta) {
                data = '<a  class="glyphicon glyphicon-remove-sign"  href="#"  onclick="deleteDayData('+data+')"></a>'
               return data;
               }

             },
            {"data": "day"},
            {"data": "time"},
            {"data": "title"},
            {"data" :"name",
 			 "render": function( data, type, full, meta) {
                if(type === 'display'){
                 var docId=full.docId;
               var imageName;
                 if(data==null){
                 imageName="";
                 }else{
                 imageName=data;
                 }

                 data = '<a  href="#" onclick="getImageByID(' + docId + ')">' + imageName + '</a>';
                 }
                 return data;
                }

             },

            {"data": "genre1"},
            {"data": "genre2"},
            {"data": "message",
             "mRender": function( data, type, full, meta,row) {

              if(type === 'display'){
               var display="";
              if(data==null || data==""){
                   display=""
               }else{
                 if(data.length > 20) {
                 display = data.substring(0,20);
                 display=display+"..."
                 }else{
                  display=data;
                  }
                }

                data = "<a  href='#' class='message_link'  >" + display + "</a>";
               /* data = '<a  href="#" onclick="return showMessage(\''+ full + '\')">' + display + '</a>';*/
                }
             return data;
               }

            },
            {"data": "link",
             "render": function( data, type, full, meta) {
            if(type === 'display'){
                data = '<a target="_blank" href="' + data + '">' + data + '</a>';
             }
             return data;
            }
          }


         ],

         columnDefs: [
            {
          render: function (data, type, full, meta) {
           return "<div class='text-wrap width-200'>" + data + "</div>";
             },
            targets: 3
              }
              ]

		});
    });



function getImageByID(docId){
showModal();
$("#modalConfirmButton").hide();
$("#imageOfModal").html('<img id="attachedImage"  src="#" alt="uploaded image" />' );
fetchImage(docId);
}

function deleteDayData(id){
showModal(id);
$("#alertOfModal").text("...Are you Sure you want to delete the data ?");

}


function showMessage(val){

showModal();
$("#modalConfirmButton").hide();

$("#alertOfModal").text(val);
}


 // Edit record
function editDayData(id){
getAddDayPage(id);
}

function deleteDay(id){
deleteDayById(id);
}


$(document).ready(function() {


    $("#example").on('click','a.message_link', function() {
          var table = $('#example').DataTable();
           var tr = $(this).closest('tr');
        var message= table.row(tr).data().message
         showMessage(message);

});



});

