var Global = {

   init : function() {
      var wrapper = document.createElement("div");
      wrapper.id = "wrapper";
      document.body.appendChild(wrapper);
      
      $("<div id='header'></div>").appendTo($("#wrapper"));
      $("<div id='outbox'></div>").appendTo($("#wrapper"));
         $("<div id='box'></div>").appendTo($("#outbox"));
      $("<div id='footer'></div>").appendTo($("#wrapper"));
   }
};