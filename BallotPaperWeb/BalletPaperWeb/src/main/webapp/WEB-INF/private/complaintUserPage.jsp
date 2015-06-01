<%@ include file="/WEB-INF/common/taglib.jsp"%>
 <link href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
 <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
 <script src="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<header>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h3>Listador por Usuario</h3>
			</div>
		</div>
		<div class="row">

			<c:if test="${messages == 'EMPTY_LIST_COMPLAINT'}">
				<label class="error">NO HA REALIZADO NINGUNA DENUNCIA</label>
			</c:if>
		</div>
		<div class="row">
			<div class="col-lg-4 col-lg-offset-4">
				<form action="findByUser.htm" method="POST">
					<input type="hidden" id=idUser name="idUser" value="${idUserLogin}"></input>
					<input type="submit" id="btnFind" value="Buscar">
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-4 col-lg-offset-4">
				<input type="search" id="search" value="" class="form-control"
					placeholder="Introduce un criterio de busqueda">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<table class="table" id="table">
					<thead>
						<tr>
							<th>Id</th>
							<th>Fecha</th>
							<th>Distrito</th>
							<th>Estado</th>
							<th>Nuero de Placa</th>
							<th>Detalle</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listComplaint}" var="listComplaintData">
							<tr>
								<td>${listComplaintData.idComplient}</td>
								<td>${listComplaintData.dateComplaint}</td>
								<td>${listComplaintData.districtComplaint}</td>
								<td>${listComplaintData.statusComplaint}</td>
								<td>${listComplaintData.plateComplaint}</td>
								<td><a id="lnkDetail" href="javascript:dialogShowDetail('${listComplaintData.idComplient}','${listComplaintData.addressComplaint}','${listComplaintData.dateComplaint}','${listComplaintData.hourComplaint}');">
										<img src="${pageContext.request.contextPath}/resources/img/Search-icon.png" alt="HTML tutorial" style="width:20px;height:20px;border:0">
									</a>
								</td>
<!-- 								<td><a id="lnkDetail" href="#">Detalle</a></td> -->
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</header>
<div >
<div id="modalShowImages" class="modal fade">
	
</div>

</div>
<script type="text/javascript">
	jQuery(document).ready(function($) {

        $('#myCarousel').carousel({
                interval: 10000
        });
 
        //Handles the carousel thumbnails
        $('[id^=carousel-selector-]').click(function () {
        var id_selector = $(this).attr("id");
        try {
            var id = /-(\d+)$/.exec(id_selector)[1];
            console.log(id_selector, id);
            jQuery('#myCarousel').carousel(parseInt(id));
        } catch (e) {
            console.log('Regex failed!', e);
        }
    });
        // When the carousel slides, auto update the text
        $('#myCarousel').on('slid.bs.carousel', function (e) {
                 var id = $('.item.active').data('slide-number');
                $('#carousel-text').html($('#slide-content-'+id).html());
        });
	});
	
	function dialogShowDetail(idComplaint,address,date,hour) {
		$('#modalShowImages').modal('show');    
		$("modalShowImages").append("");
		$.ajax({
			url : "${pageContext.request.contextPath}/getImageByComplaint.htm?id="+ idComplaint,
			data : null,
			type : "GET",
			dataType : "json",
			success : function(data) {
				 	$("#modalShowImages").empty();
				 	var imageDiv="";
					imageDiv = "<div class='modal-dialog'><div class='modal-content'><div class='modal-header'><button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>";
					imageDiv=imageDiv+"<h4 class='modal-title'>Detalle Denuncia</h4></div><div class='modal-body'>";
					imageDiv=imageDiv+"<div class='container'>"+"<div id='main_area'>"+"<div class='row'>"+"<div class='col-sm-6' id='slider-thumbs' style='display: none'>"+"<ul class='hide-bullets'>";
					for(var i = 0; i < data.length; i++) {
					imageDiv=imageDiv+"<li class='col-sm-3'>"+
						"<a class='thumbnail' id='carousel-selector-"+i+"'><img src='${pageContext.request.contextPath}/"+data[i].rootFile+"'></a>"+
						"</li>";	
					}	
					imageDiv=imageDiv+"</ul></div><div class='col-sm-6'><div class='col-xs-12' id='slider'><div class='row'><div class='col-sm-12' id='carousel-bounding-box'><div class='carousel slide' id='myCarousel'><div class='carousel-inner'>";
					for(var i = 0; i < data.length; i++) {
						if(i==0){
							imageDiv=imageDiv+"<div class='active item' data-slide-number='"+i+"'><img src='${pageContext.request.contextPath}/"+data[i].rootFile +"'></div>";	
						}else{
							imageDiv=imageDiv+"<div class='item' data-slide-number='"+i+"'><img src='${pageContext.request.contextPath}/"+data[i].rootFile +"'></div>";
						}
					}
					imageDiv=imageDiv+"</div><a class='left carousel-control' href='#myCarousel' role='button' data-slide='prev'><span class='glyphicon glyphicon-chevron-left'></span>"+" </a>"+" <a class='right carousel-control' href='#myCarousel' role='button' data-slide='next'>"+"<span class='glyphicon glyphicon-chevron-right'></span>"+"</a></div></div></div></div></div></div></div></div>";
					imageDiv=imageDiv+"<br><div><b>Fecha :</b> "+date+"</div><br><div><b>Hora : </b>"+hour+"</div><br><div><b>Dirección : </b>"+address+"</div><br><div>";
					imageDiv=imageDiv+"</div><div class='modal-footer'><button type='button' class='btn btn-default' data-dismiss='modal'>Close</button></div></div>";
					$("#modalShowImages").append(imageDiv);
					imageDiv="";
					$('#modalShowImages').modal('show');    
				},
			error: function (request, status, error) {
			alert("Error : "+request.responseText);
				}
			});
	}
</script>