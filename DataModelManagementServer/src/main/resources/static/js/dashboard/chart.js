// 차트 데이터
var data = {
    labels: ['도메인', '용어', '단어'],
    datasets: [{
        data: [80, 10, 10],
        backgroundColor: ['rgb(102, 110, 232)', 'rgb(40, 208, 148)', 'rgb(253, 172, 52)'],
    }],
};

// 차트 옵션
var options = {
    cutoutPercentage: 20, // 도넛 차트의 가운데 홈 부분 크기 설정
};

// 도넛 차트를 그릴 캔버스 가져오기
var ctx = document.getElementById('doughnutChart').getContext('2d');

// 도넛 차트 생성
var doughnutChart = new Chart(ctx, {
    type: 'doughnut',
    data: data,
    options: options,
});

// 바 차트의 데이터
var barChartData = {
    labels: ['Label 1', 'Label 2', 'Label 3'],
    datasets: [{
        label: '등록횟수',
        backgroundColor: 'rgb(102, 110, 232)',
        borderColor: 'rgb(102, 110, 232)',
        borderWidth: 1,
        data: [30, 40, 50], // 데이터 값
    }],
};

// 바 차트의 옵션
var barChartOptions = {
    scales: {
        y: {
            beginAtZero: true,
        },
    },
};

// 바 차트를 그릴 캔버스 가져오기
var barChartCtx = document.getElementById('barChart').getContext('2d');

// 바 차트 생성
var myBarChart = new Chart(barChartCtx, {
    type: 'bar',
    data: barChartData,
    options: barChartOptions,
});

// 차트 및 범례 업데이트 함수
function updateCharts(chartData) {
    // 도넛 차트 데이터 업데이트
    doughnutChart.data.labels = chartData
        .filter(item => ['word', 'domain', 'term'].includes(item.element))
        .map(item => {
            // 각 element에 대해 한글 레이블로 매핑
            switch (item.element) {
                case 'word':
                    return '단어';
                case 'domain':
                    return '도메인';
                case 'term':
                    return '용어';
                default:
                    return item.element;  // 기본적으로는 그대로 반환
            }
        });
    doughnutChart.data.datasets[0].data = chartData
        .filter(item => ['word', 'domain', 'term'].includes(item.element))
        .map(item => parseInt(item.count));


    // 바 차트 데이터 업데이트
    myBarChart.data.labels = chartData
        .filter(item => !['word', 'domain', 'term'].includes(item.element))
        .map(item => item.element);
    myBarChart.data.datasets[0].data = chartData
        .filter(item => !['word', 'domain', 'term'].includes(item.element))
        .map(item => parseInt(item.count));

    // 차트 및 범례 업데이트
    doughnutChart.update();
    myBarChart.update();
    updateLegend(chartData);
}

// 범례 업데이트 함수
function updateLegend(chartData) {
    const legendList = document.querySelector('.doughnut-legend');
    legendList.innerHTML = '';

    // 전체 개수 합산
    const totalCount = chartData
        .filter(item => ['word', 'domain', 'term'].includes(item.element))
        .reduce((acc, item) => acc + parseInt(item.count), 0);

    // 단어들의 한글 표현 매핑
    const koreanLabels = {
        'word': '단어',
        'domain': '도메인',
        'term': '용어'
    };

    chartData
        .filter(item => ['word', 'domain', 'term'].includes(item.element))
        .forEach((item, index) => {
            const listItem = document.createElement('li');
            listItem.className = `ct-series-${index} d-flex flex-column`;

            // 개수가 0일 경우를 방지하기 위해 조건 추가
            const percentage = totalCount === 0 ? 0 : ((parseInt(item.count) / totalCount) * 100).toFixed(2);

            listItem.innerHTML = `
                <h5 class="mb-0">${koreanLabels[item.element]}</h5>
                <span class="badge badge-dot my-2 cursor-pointer rounded-pill" 
                      style="background-color: ${item.color};width:35px; height:6px;"></span>
                <div class="text-muted">${item.count} 개 (${percentage}%)</div>
            `;
            legendList.appendChild(listItem);
        });
}


// Ajax 요청 에러 처리 함수
function handleAjaxError() {
    showAlert("서버 오류가 발생했습니다. 다시 시도해 주세요.");
}

// Select List 값 변경 이벤트 처리 함수
function handleSelectChange() {
    const selectElement = document.getElementById("selectStandardArea");
    const standardAreaName = selectElement.options[selectElement.selectedIndex].text;
    sendAjaxRequest(
        "/dms/dashboard/chart",
        standardAreaName,
        function (response) {
            showLoadingSpinner();
            console.log("차트 그리기 전 테스트", response);
            // 차트 및 범례 업데이트
            updateCharts(response);
            hideLoadingSpinner();
        }
    );
    sendAjaxRequest(
        "/dms/session/set",
        standardAreaName,
        function (response) {
            showLoadingSpinner();
            console.log(response);
            hideLoadingSpinner();
        }
    )
    hideLoadingSpinner();
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

document.addEventListener('DOMContentLoaded', function () {
    showLoadingSpinner();
    fetchDataFromServer();
    const standardAreaName = "FirstChartLoad";
    sendAjaxRequest(
        "/dms/dashboard/chart",
        standardAreaName,
        function (response) {
            console.log("최초 차트 요청 결과", response);
            // 차트 및 범례 업데이트
            updateCharts(response);
            hideLoadingSpinner();
        }
    );
    hideLoadingSpinner();
});
const selectElement = document.getElementById("selectStandardArea");

function fetchDataFromServer() {
    showLoadingSpinner();
    sendAjaxRequest(
        "/dms/session/get",
        null,
        function (response) {
            selectOptionBasedOnData(response);
            hideLoadingSpinner();
        }
    )
}

function selectOptionBasedOnData(selectedOption) {
    for (var i = 0; i < selectElement.options.length; i++) {
        if (selectElement.options[i].value === selectedOption) {
            selectElement.selectedIndex = i;
            break;
        }
    }
}