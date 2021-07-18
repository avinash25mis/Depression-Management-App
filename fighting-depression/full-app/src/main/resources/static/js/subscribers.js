 var div=$("#subscriberPageId")
 startAppLoader(div);
$(document).ready(function() {

        var data2;

        $.ajax({
              url:  apiUrl+"/Subscriber/all",
              type: "GET",
             headers:{
            "Authorization": "Bearer " + window.sessionStorage.getItem("authTokenId")
             },
              dataType: "json",
            contentType: "application/json",
            async:false,

        success: function (d) {
         stopAppLoader(div);
         data2=d;

        },
       error: function (jqXHR) {
        alert(jqXHR.responseJSON.message);

        },

    });

        $('#example2').DataTable({
        "processing": false,
        "serverSide": false,
          "scrollY":  true,
          "scrollX":  true,
       "scrollCollapse": true,
       "fixedColumns": true,
         "data":  data2,



       "columns": [

                      {"data": "firstName"},
                      {"data": "lastName"},
                      {"data": "gender"},
                      {"data": "age"},
                      {"data": "email"},
                      {"data": "feedback"},
             {
               data: "id",
               "render": function( data, type, full, meta) {
                data = '<a  class="glyphicon glyphicon-remove-sign"  href="#"  onclick="deleteSubscriberData('+data+')"></a>'
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


  function   deleteSubscriberData(id){
           $.ajax({
                url: apiUrl+"/Subscriber/"+id,
                type: "DELETE",
                   headers:{
              "Authorization": "Bearer " + window.sessionStorage.getItem("authTokenId")
               },

         success: function (jqXHR) {
             getAllSubscribers();
          },
         error: function (jqXHR) {
          alert(jqXHR.responseJSON.message);

          }

           });

  }