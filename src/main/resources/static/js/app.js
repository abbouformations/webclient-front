function showCreateForm() {
    if(!courbes[id]){
        courbes[id]=new TimeSeries({scrollBackwards:true,tooltip:true});
        let color=colorRandom();
        chart.addTimeSeries(courbes[id],color);
        var connection = new EventSource("/showform");
        connection.onmessage=function (resp) {
            var transaction=JSON.parse(resp.data);
            courbes[id].append(new Date().getTime(),transaction.price);
        };
        btn.style.background='#FF0000';

    }
}