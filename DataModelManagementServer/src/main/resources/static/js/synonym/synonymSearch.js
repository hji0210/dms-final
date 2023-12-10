// 라벨 클릭 이벤트 리스너 추가
document.getElementById("searchLabel").addEventListener("click", function () {
    searchSynonymList();
});

document.getElementById("synonymSearch").addEventListener("keypress", function (e) {
    if (e.key === 'Enter') { // 엔터키 눌렀을 때
        searchSynonymList();
    }
});

function searchSynonymList() {
    // 검색 기능을 이곳에 구현
    const searchWord = document.getElementById("synonymSearch");
    const stdAreaId = document.getElementById("selectStandardArea");
    const conditionState = {
        dicLogNm: searchWord.value //무조건 object,string타입
        ,stdAreaId: stdAreaId.value
    };
    sendCheckedStateToServer(conditionState);
}

function handleAjaxError() {
    showAlert("서버 오류가 발생했습니다. 다시 시도해 주세요.");
}

function toggleCollapse() {
    const tableRows = document.querySelectorAll('.table tbody tr');

    tableRows.forEach(function(row) {
        row.addEventListener('click', function(event) {
            const dicId = row.getAttribute('data-bs-target');
            if(dicId) {
                const childTrTags = document.querySelectorAll(`tbody tr[data-bs-parent="${dicId}"]`);
                childTrTags.forEach(function(childRow) {
                    if(childRow.classList.contains('show')) {
                        childRow.classList.remove('show');
                    } else {
                        childRow.classList.add('show');
                    }
                });
            }
        });
    });
}

function sendCheckedStateToServer(conditionState) {
    console.log(conditionState)
    sendAjaxRequest(
        "/dms/synonym/searchList",
        conditionState,
        function (response) {
            if (response && Array.isArray(response)) {
                response.forEach(function (dataDTO) {
                    const tableBody = document.querySelector('.table tbody');
                    tableBody.innerHTML = '';
                    let rowNumber = 1;
                    response.forEach(function (dataDTO) {
                        const newRow = document.createElement('tr');
                        const arrowTag = document.createElement('td');
                        if(dataDTO.standardYn === 'Y') {
                            arrowTag.innerHTML = `+`;
                            arrowTag.setAttribute('font-size', '20px');
                            newRow.setAttribute('aria-expanded', 'false');
                            newRow.setAttribute('data-bs-toggle', 'collapse');
                            newRow.setAttribute('data-bs-target', `r${dataDTO.dicId}`);
                        }else {
                            arrowTag.innerHTML = `  ↳`;
                            arrowTag.setAttribute('font-size', '15px');
                            newRow.classList.add('collapse', 'accordion-collapse');
                            newRow.setAttribute('data-bs-parent', `r${dataDTO.altDicId}`);
                            newRow.style.backgroundColor = '#F5F5F5';
                        }
                        newRow.innerHTML = `
                             <td>${rowNumber++}</td>
                             <td>${dataDTO.stdAreaNm || ""}</td>
                             <td>${dataDTO.dicGbnCd || ""}</td>
                             <td>${dataDTO.standardYn || ""}</td>
                             <td>${dataDTO.entClssYn || ""}</td>
                             <td>${dataDTO.attrClssYn || ""}</td>
                             <td>${dataDTO.dicLogNm || ""}</td>
                             <td>${dataDTO.dicPhyNm || ""}</td>
                             <td>${dataDTO.dicDesc || ""}</td>`;
                        newRow.insertBefore(arrowTag, newRow.firstChild); // newRow 맨앞에 arrow tag 추가
                        tableBody.appendChild(newRow);
                    });
                    toggleCollapse();
                });
            }
            showAlert("검색이 완료되었습니다.", "info");
            document.getElementById("synonymSearch").value = "";
        },
        function () {
            handleAjaxError();
            document.getElementById("synonymSearch").value = "";
        }
    );
}

