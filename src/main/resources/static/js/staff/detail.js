function unlock(staffCode) {
	Swal.fire({
	  text: "차단을 해제하시겠습니까?",
	  icon: "question",
	  showCancelButton: true,
	  confirmButtonColor: "#3085d6",
	  cancelButtonColor: "#d33",
	  confirmButtonText: "확인",
		cancelButtonText: "취소"
	}).then((result) => {
	  if (result.isConfirmed) {
	    location.href = `/staff/${staffCode}/unlock`
	  }
	});
}

function disable(staffCode) {
	Swal.fire({
	  text: "계정을 비활성화 하시겠습니까?",
	  icon: "question",
	  showCancelButton: true,
	  confirmButtonColor: "#3085d6",
	  cancelButtonColor: "#d33",
	  confirmButtonText: "확인",
		cancelButtonText: "취소"
	}).then((result) => {
	  if (result.isConfirmed) {
	    location.href = `/staff/${staffCode}/disable`
	  }
	});
}

function enable(staffCode) {
	Swal.fire({
	  text: "계정을 활성화 하시겠습니까?",
	  icon: "question",
	  showCancelButton: true,
	  confirmButtonColor: "#3085d6",
	  cancelButtonColor: "#d33",
	  confirmButtonText: "확인",
		cancelButtonText: "취소"
	}).then((result) => {
	  if (result.isConfirmed) {
	    location.href = `/staff/${staffCode}/enable`
	  }
	});
}