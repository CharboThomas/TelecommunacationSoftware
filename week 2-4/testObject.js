class myRequest {

	constructor(url){
		this.url = url;

		let maRequest = new XMLHttpRequest();
		maRequest.open("GET",url,false);
		maRequest.send(null);

		this.data = JSON.parse(maRequest.responseText);

	}

	afficher(){
		console.log(this.data);
	}

	cleanData(){

		let this.listData = [
			"course : " + data.course, "course Active : " + data.courseActive, "course Name : " + data.courseName,
			"dropped Students : " + data.droppedStudents, "date : " + data.date,"release Year : " + data.releaseYear,

			data.someData[0][0],data.someData[0][1],data.someData[1][0], data.someData[1][1],
			data.someData[2][0],data.someData[2][1],data.someData[3][0],data.someData[3][1],

	        data.scores["a"], data.scores["b"], data.scores["c"]
		]


		let this.listTarget = [
			"dataCourse","dataActive","dataName","dataDrop","dataDate","dataYear",

			"data2","data3","data4","data5","data6","data7","data8","data9",

	        "data0.1","data0.2","data0.3"
		]

	}

	printDataOnHTML(){

		for(let i = 0 ; i < 6 ; i++){

			document.getElementById(this.listTarget[i]).innerHTML = this.listData[i];

		}

	}


	generateGraph1(){

		var xValues = ["a","b","c"];
		var yValues = [data.scores["a"],data.scores["b"],data.scores["c"]];
		var barColors = ["red", "green","blue"];

		new Chart("monCanvas", {
			type: "bar",
			data: {
				labels: xValues,
				datasets: [{
					backgroundColor: barColors,
					data: yValues
					}]
				},
				options: {
					legend: {display: false},
					title: {
					    display: true,
					    text: "scores"
					}
				}
			}
		);
	}

	generateGraph2(){

		var xyValues = [
			{x:data.someData[0][0], y:data.someData[0][1]},
			{x:data.someData[1][0], y:data.someData[1][1]},
			{x:data.someData[2][0], y:data.someData[2][1]},
			{x:data.someData[3][0], y:data.someData[3][1]}
		];

		new Chart("myChart", {
			type: "scatter",
			data: {
				datasets: [{
				    pointRadius: 4,
				    pointBackgroundColor: "rgb(0,0,255)",
				    data: xyValues
				}]
			},
			options: {
				legend: {display: false},
				scales: {
				    xAxes: [{ticks: {min: 0, max:50}}],
				    yAxes: [{ticks: {min: 0, max:5}}],
				}
			}
		});
	}


}