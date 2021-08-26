Vue.component('base-online-restaurant', {
template:
`
<div id= "baseOnlineRestaurant">

</div>

`,
   
    mounted() {
        $('#baseDialogForm').modal('show');
        let style = document.createElement('link');
        style.type = "text/css";
        style.rel = "stylesheet";
        style.href = 'css/baseOnlineRestaurant.css';
        document.head.appendChild(style);
    },    
});