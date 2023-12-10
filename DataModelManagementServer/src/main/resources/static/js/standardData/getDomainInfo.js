
function getDomainInfo(domId) {
    if (!domId) {
        console.error('dicId is not available');
        return;
    }


    sendAjaxRequest(
        "/dms/single-domain/selectRest",
        {
            standardAreaName: document.getElementById('selectStandardArea').value,
            domId: domId
        },
        function (response) {
            insertEachDomainTag(response.receivedDomainDTO);
        },

        function () {
            handleAjaxError();
            // Handle AJAX error
        }
    );
}

function insertEachDomainTag(receivedDomainDTO) {
    if (receivedDomainDTO) { // Added a check for the existence of receivedDomainDTO

        document.getElementById('stdModalDomId').value = receivedDomainDTO.domId || '';
        document.getElementById('stdModalkeyDomName').value = receivedDomainDTO.keyDomName || '';
        document.getElementById('stdModalDomName').value = receivedDomainDTO.domName || '';
        document.getElementById('stdModaldomTypeCode').selected = true;
        document.getElementById('stdModalDomGroupId').value = receivedDomainDTO.domGroupId || '';
        document.getElementById('stdModalDomdataTypeCode').selected = true;
        document.getElementById('stdModaldataLen').value = receivedDomainDTO.dataLen || '';
        document.getElementById('stdModalDataScale').value = receivedDomainDTO.dataScale || '';
        document.getElementById('stdModalDataMin').value = receivedDomainDTO.dataMin || '';
        document.getElementById('stdModalDataMax').value = receivedDomainDTO.dataMax || '';
        document.getElementById('stdModaldomDescription').value = receivedDomainDTO.domDescription || '';

        getDomainGroup(receivedDomainDTO.domGroupId);
    }
}

//도메인 그룹
function getDomainGroup(domGroupId) {
    $.ajax({
        url: "/dms/single-domain/group",
        type: "POST",
        contentType: "application/json",
        data: {},
        success: function (data, status, xhr) {
            // data 배열을 순회하면서 각 요소의 domGrpId가 비어있지 않은 경우, select 요소에 새로운 option 추가
            $("select#stdModalDomGroupId option").remove();
            $("select#stdModalDomGroupId").append(`<option value="" disabled>도메인 그룹</option>`);
            for (let i = 0; i < data.length; i++) {
                // data[i]의 domGrpId가 비어있지 않은지 확인, // 조건을 만족하는 경우에만 아래의 작업을 수행
                if (data[i].domGrpId !== '') {
                    // 현재 data 요소의 domGrpId와 domGrpNm 값을 변수에 할당
                    let grpId = data[i].domGrpId;
                    let grpNm = data[i].domGrpNm;
                    if (domGroupId === grpId) {
                        $("select[name='domGroupId']").append("<option value='" + grpId + "' selected>" + grpNm + "</option>");
                    } else {
                        $("select[name='domGroupId']").append("<option value='" + grpId + "'>" + grpNm + "</option>");
                    }
                }
            }
        }
    });
}