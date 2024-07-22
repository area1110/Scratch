jQuery(function () {
	$("#btnOpenPopup").on("click", function () {
		w2popup.load({
			url: "/popup.html",
			onOpen: "onOpen",
		});
	});

});

function onOpen() {

}
