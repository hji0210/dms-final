let firstFlag = true;

function getStandardAreaId() {
    return document.getElementById("stdAreaId").value.trim();
}

function getDifferenceCombined() {
    return document.getElementById("differenceCombined").value.trim();

}


function getSearchWord() {
    return document.getElementById("searchWord").value.trim();
}

function isSearchWordEmpty() {
    return getSearchWord() === "";
}

function getStandardAreaName() {
    return document.getElementById("selectStandardArea").value.trim();
}

function getStandardId() {
    return document.getElementById("selectStandardArea").value.trim();
}


function sendAjaxRequest(url, data, successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(data),
        contentType: "application/json",
        success: successCallback,
        error: errorCallback,
    });
}


function getWordList() {
    const stdAreaId = getStandardAreaName();
    const searchWord = getSearchWord();

    if (isSearchWordEmpty()) {
        showAlert("검색어를 입력하세요.");
    } else {
        sendAjaxRequest(
            "/dms/single-term/searchWordRest",
            {
                stdAreaId: stdAreaId, searchWord: searchWord},
            function (response) {
                console.log(response.wordList); // 콘솔에 출력하여 확인

                updateWordTable(response.wordList);
                updateInputValues(response.wordList); // 여기서 updateInputValues() 호출
                appendRow(response); // appendRow 함수에 응답 데이터 전달
                handleSelectCheckResponse();

            },
            function () {
                handleAjaxError();
            }
        );
    }
}

function handleSelectCheckResponse() {
    showAlert("검색이 성공적으로 완료되었습니다", "info");

}

// dicLogNm과 dicPhyNm을 합산하고 공백을 제외한 값을 각각의 input 요소에 넣는 함수
let lastCheckedRowIndex = -1; // 최근에 체크된 행의 인덱스를 저장하는 변수
function updateInputValues(wordList) {
    let concatenatedDicLogNm = '';
    let concatenatedDicPhyNm = '';

    // wordList에서 dicGbnCd가 'word'인 경우만 처리
    const filteredWordList = wordList.filter(word => word.dicGbnCd === 'word');

    // 'word'인 경우에만 dicLogNm과 dicPhyNm을 연결
    filteredWordList.forEach((word, rowIndex) => {
        concatenatedDicLogNm += word.dicLogNm + ' ';
        concatenatedDicPhyNm += word.dicPhyNm + ' ';

        // 각 체크박스에 이벤트 리스너 추가
        const checkboxes = document.querySelectorAll('#wordTableBody tr:nth-child(' + (rowIndex + 1) + ') input[type="checkbox"]');
        checkboxes.forEach((checkbox) => {
            checkbox.addEventListener('change', function () {
                if (checkbox.checked) {
                    // Checkbox checked: Store the index of the row
                    lastCheckedRowIndex = rowIndex;
                    console.log("rowIndex= " + rowIndex);
                }
                // 입력 값 및 필드 업데이트
                const inputDicLogNm = document.getElementById('dicLogNm');
                const inputDicPhyNm = document.getElementById('dicPhyNm');

                if (!checkbox.checked) {
                    concatenatedDicLogNm = concatenatedDicLogNm.replace(word.dicLogNm + ' ', '');
                    concatenatedDicPhyNm = concatenatedDicPhyNm.replace(word.dicPhyNm + ' ', '');
                } else {
                    concatenatedDicLogNm += word.dicLogNm + ' ';
                    concatenatedDicPhyNm += word.dicPhyNm + ' ';
                }

                inputDicLogNm.value = concatenatedDicLogNm.trim();
                inputDicPhyNm.value = concatenatedDicPhyNm.trim();
            });
        });
    });

    // 초기 입력 필드에 연결된 값 설정
    const inputDicLogNm = document.getElementById('dicLogNm');
    const inputDicPhyNm = document.getElementById('dicPhyNm');
    inputDicLogNm.value = concatenatedDicLogNm.trim();
    inputDicPhyNm.value = concatenatedDicPhyNm.trim();
}

function updateWordTable(wordList, differenceWord) {
    const tableBody = document.getElementById('wordTableBody');
    console.log(wordList); // 여기에 추가

    // 기존 테이블의 내용을 지우기
    tableBody.innerHTML = '';

    if (Array.isArray(wordList)) {
        wordList.forEach(function (word, index) {
            console.log("word.dicGbnCd= " + word.dicGbnCd); // Log the dicGbnCd value for each word (for debugging)

            if (word.dicGbnCd === 'word') {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${index + 1}</td>
                    <td><input type="checkbox" checked id="termCheckBox"/></td>
                    <td>${word.dicLogNm}</td>
                    <td>${word.dicPhyNm}</td>
                    <td><input type="checkbox" ${word.entClssYn === 'Y' ? 'checked' : ''} disabled/></td>
                    <td><input type="checkbox" ${word.attrClssYn === 'Y' ? 'checked' : ''} disabled/></td>
                    <td>${word.dicDesc}</td>
                    <td>${word.dicId}</td>
                    <!-- 추가할 데이터 항목들을 여기에 계속해서 추가하세요 -->
                `;
                tableBody.appendChild(row);

                if (firstFlag === true) {
                    if (index === wordList.length - 1 && word.dicGbnCd === 'word') {
                        lastCheckedRowIndex = index; // Update lastCheckedRowIndex with the index of the last row
                        console.log("lastCheckedRowIndex 갱신시: " + lastCheckedRowIndex);
                        firstFlag = false;
                    } else if (firstFlag === false) {
                        lastCheckedRowIndex = index - 1;
                        console.log("lastCheckedRowIndex 마이너스 1: " + lastCheckedRowIndex);

                    }
                }

            }
            updateTerm(wordList);

        });
    } else {
        console.error("Word list is not defined or is not an array.");
        // Handle the case where wordList is not an array or is undefined
        // For example, show an error message or handle it accordingly
    }
}

function updateTerm(wordList) {
    console.log("이거 실행은 되냐 updateTerm?");
    let termName = null; // 용어 이름을 담을 변수

    if (Array.isArray(wordList)) {
        wordList.forEach(function (word) {
            if (word.dicGbnCd === 'term') {
                termName = word.dicLogNm; // 용어 이름 할당
                console.log("Found term:", termName); // 용어 이름 로그 출력
                return; // term이 하나만 조회되면 루프를 종료합니다.
            }
        });
    }

    const equalWordSelectedInput = document.getElementById("equalWordSelected");
    equalWordSelectedInput.value = termName !== null ? termName : '존재하지 않는 용어입니다.'; // 용어 이름을 input 요소에 할당
}

let modalTermWord;

function appendRow(response) {
    const tableBody = document.getElementById('wordTableBody');
    if (response.differenceCombined) {
        const row = document.createElement('tr');

        const cell1 = document.createElement('td');
        cell1.textContent = '※'; // Add content or leave empty based on the table structure
        row.appendChild(cell1);

        const cell2 = document.createElement('td');
        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox'; // Creating a checkbox
        // Set checkbox attributes or classes if needed
        cell2.appendChild(checkbox);
        row.appendChild(cell2);

        // Cell 3 content
        const cell3 = document.createElement('td');
        cell3.textContent = response.differenceCombined.trim(); // Set content based on response
        row.appendChild(cell3);

        // Empty cells 4, 5, 6 with colspan 3
        const emptyCell = document.createElement('td');
        emptyCell.textContent = '존재하지 않는 단어입니다.';
        emptyCell.setAttribute('colspan', '3'); // 3칸을 합쳐서 표시
        row.appendChild(emptyCell);

        // Cell 7 - Button
        const cellForButton = document.createElement('td');
        cellForButton.style.textAlign = 'left'; // Align button to the left within the cell

        const button = document.createElement('button');
        button.textContent = '등록';
        button.classList.add('btn', 'btn-primary');

        // Add event listener to the button to show the modal

        button.addEventListener('click', function () {
            // Show the modal when the button is clicked
            modalTermWord = new bootstrap.Modal(document.getElementById('modalTermWord'));
            modalTermWord.show();

            // Set the value of modalLogNm input to response.differenceCombined.trim()
            const modalLogNmInput = document.getElementById('modalLogNm');
            modalLogNmInput.value = response.differenceCombined.trim();
        });

        cellForButton.appendChild(button);
        row.appendChild(cellForButton);


        // Cell 8 - Empty cell
        const cell8 = document.createElement('td');
        cell8.setAttribute('colspan', '1'); // Set colspan to 1 for the empty cell
        row.appendChild(cell8);

        // Add the row to the table body
        tableBody.appendChild(row);

    }
}


function handleAjaxError() {
    showAlert("서버 오류가 발생했습니다. 다시 시도해 주세요.");
}

document.getElementById("selectWordButton").addEventListener("click", function () {
    getWordList();
});


// ---------------- 인서트 --------------------------
document.getElementById("insertButton").addEventListener("click", function () {
    insertWordAndTerm(); // insertWordAndTerm() 함수 호출
});

function getDicId() {
    return document.getElementById("dicId").value.trim();
}

function getDicLogNm() {
    return document.getElementById("dicLogNm").value.trim();
}

//dicGbnCd

function getDicPhyNm() {
    return document.getElementById("dicPhyNm").value.trim();
}

function getDicDesc() {
    return document.getElementById("dicDesc").value.trim();
}


// 클릭 이벤트 핸들러 안에서 selectedDomainId 값 설정
// 예: selectedDomainId = domain.domId;

function getDomId() {
    console.log("텀인서트 돔아이디 ", selectedDomainId);

    return selectedDomainId;
}

function isDomIdEmpty() {
    return getDomId() === "";
}


let wordList = []; // 객체 대신 배열로 초기화


function getLastWordAttrClssValue() {
    const table = document.getElementById('wordTableBody');

    console.log(" lastCheckedRowIndex 잘 나오고 있냐? " + lastCheckedRowIndex);


    if (lastCheckedRowIndex !== -1) {




        const attrClssValue = table.rows[lastCheckedRowIndex].cells[5].querySelector('input[type="checkbox"]').checked;
        console.log("attrClssValue= " + attrClssValue);

        console.log("lastCheckedRowIndex= " + lastCheckedRowIndex);


        return attrClssValue;
    }

    return false;
}

function getCheckedDicIds() {
    const table = document.getElementById('wordTableBody');
    const splitWordsForTerm = getDicLogNm();
    const dicLogNmForTerm = splitWordsForTerm.split(" ").map(word => word.trim());
    console.log("dicLogNmForTerm= ", dicLogNmForTerm);

    const checkedDicIds = [];

    for (let i = 0; i < dicLogNmForTerm.length; i++) {
        const wordToFind = dicLogNmForTerm[i];

        for (let j = 0; j < table.rows.length; j++) {
            const tablesDicLogNm = table.rows[j].cells[2].textContent.trim();

            if (tablesDicLogNm === wordToFind) {
                const dicIdCell = table.rows[j].cells[7];
                const dicId = dicIdCell.textContent.trim();
                checkedDicIds.push({id: dicId, orderNo: i + 1});
                break;
            }
        }
    }

    console.log("Updated Checked dicIds: ", checkedDicIds);
    return checkedDicIds;
}


function insertWordAndTerm() {

    const stdAreaId = getStandardAreaName();
    const dicLogNm = getDicLogNm();
    const dicPhyNm = getDicPhyNm();
    const dicDesc = getDicDesc();
    const domId = getDomId();

    console.log("논리 + 물리명= " + dicLogNm + "-" + dicPhyNm);

    if (isDomIdEmpty()) {
        showAlert("도메인을 선택하세요.");
    } else {
        const attrClssValue = getLastWordAttrClssValue();

        if (!attrClssValue) {
            showAlert("마지막 단어는 속성 분류어가 선택되어야 합니다.");
        } else {
            const checkedDicIds = getCheckedDicIds();

            const termDTOList = checkedDicIds.map((checkedItem, index) => {
                return {
                    dicId: checkedItem.id,
                    orderNo: index + 1
                };
            });

            const termWordDTO = {
                wordDTO: {
                    stdAreaId: stdAreaId,
                    dicLogNm: dicLogNm,
                    dicPhyNm: dicPhyNm,
                    dicDesc: dicDesc,
                    domId: domId,

                },
                termDTOList: termDTOList,
            };

            sendAjaxRequest(
                "/dms/single-term/insertRest",
                termWordDTO,
                function (response) {
                    handleInsertCheckResponse();
                    clearTermInputFields();

                },

                function () {
                    handleAjaxError();

                }
            );
        }
    }
}



function handleInsertCheckResponse() {

    showAlert("용어 등록이 성공적으로 완료되었습니다.", "info");

}


function disableInsertButton() {
    document.getElementById("insertButton").setAttribute("disabled", "true");
}

function enableInsertButton() {
    document.getElementById("insertButton").removeAttribute("disabled");
}

function handleTermDuplicateCheckResponse(response) {
    if (response.isDuplicate) {
        showAlert("중복된 용어입니다. 다른 용어를 입력하세요.");
        disableInsertButton();
    } else {
        showAlert("사용 가능한 용어입니다", "success");
        enableConfirmButton();
    }
}


function clearTermInputFields() {
    document.getElementById("selectWordButton").value = "";
    document.getElementById("equalWordSelected").value = "";
    document.getElementById("dicLogNm").value = "";
    document.getElementById("dicPhyNm").value = "";
    document.getElementById("dicDesc").value = "";
    document.getElementById("termDomName").value = "";

    // 체크박스 선택 해제
    const checkboxes = document.querySelectorAll('#wordTableBody input[type="checkbox"]');
    checkboxes.forEach((checkbox) => {
        checkbox.checked = false;
    });

    // wordTableBody 초기화
    const tableBody = document.getElementById('wordTableBody');
    tableBody.innerHTML = '';
}

