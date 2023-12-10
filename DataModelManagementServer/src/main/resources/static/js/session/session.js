document.addEventListener('DOMContentLoaded', function () {
    fetchDataFromServer();
});
const selectElement = document.getElementById("selectStandardArea");

function fetchDataFromServer() {
    console.log("fetchDataFromServer 함수 호출");
    sendAjaxRequest(
        "/dms/session/get",
        null,
        function (response) {
            console.log("서버 응답: ", response);
            selectOptionBasedOnData(response);
        }
    )
}

function handleSelectChange() {
    const selectedStandardArea = selectElement.options[selectElement.selectedIndex].text
    sendAjaxRequest(
        "/dms/session/set",
        selectedStandardArea,
        function (response) {
            console.log(response);
        }
    )
}

function selectOptionBasedOnData(selectedOption) {
    console.log("함수 진입");
    for (var i = 0; i < selectElement.options.length; i++) {
        if (selectElement.options[i].value === selectedOption) {
            selectElement.selectedIndex = i;
            break;
        }
    }
}