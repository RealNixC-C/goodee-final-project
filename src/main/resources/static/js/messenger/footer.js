/**
 * footer.jsp
 * 채팅창이 열리도록 하는 역할
 */
function openMessenger() {
	window.open(
		"/msg",
		"MessengerWindow",
		"width=400,height=620,resizable=no,scrollbars=yes"
	);
}

function openFault() {
	window.open(
		"/fault/write",
		"FaultWindow",
		"width=400,height=520,resizable=no,scrollbars=yes"
	);
}