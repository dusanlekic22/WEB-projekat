Vue.component('base-dialog', {
template:
`
<div id= "baseDialogOverlay"> 
  <div class="modal" id="baseDialogForm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" data-backdrop='static' data-keyboard="false" >
  <div class="modal-dialog modal-dialog-centered" role="document">
   <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header border-bottom-0">
         <slot name="header"></slot>
      </div>
       <slot name="body"></slot>
    </div>
      <div class="modal-footer">
        <slot name="footer"></slot>
     </div>
    </div>
  </div>
</div>
</div>

  
  
`,
   
    mounted() {
        $('#baseDialogForm').modal('show');
        let style = document.createElement('link');
        style.type = "text/css";
        style.rel = "stylesheet";
        style.href = 'css/baseDialog.css';
        document.head.appendChild(style);
    },    
    methods: {
        closeRegistration() {
            this.$store.commit("closeRegistration");
      },
      closeLogin() {
      this.$store.commit('loginModule/closeLogin');
     },
      check(evt) {
        console.log("zatvaranje")
        console.log(evt);
        if (evt.srcElement.id === "baseDialogForm") {
          $("#baseDialogForm").mousedown(function(){
        console.log("Mouse button released.");
}); 
          this.closeRegistration();
          this.closeLogin();
         
        }
      },
    },
});