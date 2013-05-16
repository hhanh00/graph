$ ->
  $(".btn").click -> 
    t = $(this).text()
    $.getJSON('graph.json', {symbol: t}, (data) ->
      name = data.name
      categories = for bar in data.bars
        bar.x
      series = for bar in data.bars
        bar.y
      graph = {
        chart : {
          type : 'column'
        },
        title : {
          text : 'Latency'
        },
        plotOptions : {
          column : {
            pointPadding : 0,
            borderWidth : 0,
            groupPadding : 0,
            shadow : false
          }
        },
        xAxis : {
          categories : categories,
          labels: {
            step: 10
          }
        },
        yAxis : {
          title : {
            text : 'count'
          }
        },
        series : [{
            name : name,
            data : series
          }
        ]
      }
      
      $('#container').highcharts(graph)
      )
    


