 var div=$("#viewDayPageId")
 startAppLoader(div);
$(document).ready(function() {
        var data;

        $.ajax({
              url:  "/viewDayPage/data",
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
            {"data": "message"},
            {"data": "link",
             "render": function( data, type, full, meta) {
            if(type === 'display'){
                data = '<a target="_blank" href="' + data + '">' + data + '</a>';
             }
             return data;
            }
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
$("#alertOfModal").text("Are you Sure you want to delete the data ?");

}


 // Edit record
function editDayData(id){
getAddDayPage(id);
}

function deleteDay(id){
deleteDayById(id);
}

