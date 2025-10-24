/**
 * list.jsp
 */
// 이니셜라이즈
fetch("/msg/unread/count", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(rooms)
})
.then(res => res.json())
.then(data => {
    let unread = data.unread;
    let latest = data.latest;
    let today = new Date();
    if (unread != null && latest != null) {
        for (const chatRoomNum in unread) {
            let badge = document.querySelector('#unread-count-' + chatRoomNum);
            badge.innerText = unread[chatRoomNum] > 0 ? unread[chatRoomNum] : "";

            let latestMessage = document.querySelector('#chat-room-last-' + chatRoomNum); 
			if (latest[chatRoomNum].chatBodyContent.trim().substr(0, 17).includes('\n')) {
				latestMessage.innerText = latest[chatRoomNum].chatBodyContent.trim().split('\n')[0] + '...';
			} else if (latest[chatRoomNum].chatBodyContent.length >= 17) {
				latestMessage.innerText = latest[chatRoomNum].chatBodyContent.trim().substr(0, 17) + '...';				
			} else {
	            latestMessage.innerText = latest[chatRoomNum].chatBodyContent.trim();				
			}

            let time = document.querySelector('#time-' + chatRoomNum);
            let timeFromJava = latest[chatRoomNum].chatBodyDtm;
            const timeToJs = new Date(timeFromJava);
            if (latest[chatRoomNum].chatBodyContent == '메시지 없음') {
                time.innerText = '';
            } else {
                if (timeToJs.getDate() == today.getDate()) {
                    time.innerText = timeToJs.getHours() + '시 ' + timeToJs.getMinutes() + '분';
                } else if (timeToJs.getDate() == today.getDate() - 1) {
                    time.innerText = '어제';
                } else {
                    time.innerText = timeToJs.getMonth() + 1 + '월 ' + timeToJs.getDate() + '일';
                }
            }
        }
    }
});
// 채팅방 입장
const forms = document.querySelectorAll('.chat-room');
forms.forEach(form => {
    form.addEventListener('click', () => {
        form.submit();
    });
});
// 채팅방 타입 변경
const formAll = document.querySelector('#formAll');
const formDm = document.querySelector('#formDm');
const formGroup = document.querySelector('#formGroup');
const tabAll = document.querySelector('.tab-all');
const tabDm = document.querySelector('.tab-dm');
const tabGroup = document.querySelector('.tab-group');
tabAll.addEventListener('click', () => {
	formAll.submit();
});
tabDm.addEventListener('click', () => {
	formDm.submit();
});
tabGroup.addEventListener('click', () => {
	formGroup.submit();
});