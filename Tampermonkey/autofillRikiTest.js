main();

function main() {
	let [testID, projectId] = extractHomworkId();
	if (!testID) return;
	$.ajax({
		method: "GET",
		url: `https://fpt-software.riki.edu.vn/online/api/jlpt/findTestById?ID=${testID}&project_id=${projectId}`,
		success: function (response) {
			processExamPart(response.data);
		},
	});
}

function processExamPart(data) {
	let examParts = data.test.exam_part;

	for (let examPart of examParts) {
		let currentExamPartId = selectCurrentTestPart();

		for (let examPart2 of examPart.exam_part2) {
			let groups = examPart2.groups;
			for (let group of groups) {
				let questions = group.questions;
				for (let question of questions) {
					let questionId = question.ID;
					let answers = question.exam_answers;
					for (let answer of answers) {
						let answerId = answer.ID;
						if (answer.Point > 0) {
							console.log(answerId);
							selectCorrectAns(currentExamPartId, questionId, answerId);
						}
					}
				}
			}
		}
	}
}

function selectCurrentTestPart(){
    let pageTestCourseContentElements = $('.page-test-course--content');
    for(let pageTestCourseContentElement of pageTestCourseContentElements){
        if(pageTestCourseContentElement.style.display !== "none"){
            let form = pageTestCourseContentElement.children[0].children[0];
			let formId = form.getAttribute('id');
			const formIdRegex = /test-part-id-(\d+)/;
			let testPartId = formId.match(formIdRegex);

            return testPartId[1];
        }
    }
}

function selectCorrectAns(examId, questionId, answerId) {
	let correnctAnsInput = $(`#test-part-id-${examId} label[for=answer${answerId}]`);
	correnctAnsInput.trigger("click");
}

function extractHomworkId() {
	let currentUrl = window.location.href;

	const testIdRegex = /testId=(\d+)/;
	const projectIdRegex = /project_id=(\d+)/;

	// Extract the numbers using regex
	const testIdMatch = currentUrl.match(testIdRegex);
	const projectIdMatch = currentUrl.match(projectIdRegex);

	// Check if matches are found
	if (testIdMatch && projectIdMatch) {
		const testId = testIdMatch[1]; // Extracted testId
		const projectId = projectIdMatch[1]; // Extracted projectId
		return [testId, projectId];
	} else {
		console.log("No number found for either testId or projectId in the URL.");
		return [null, null];
	}
}
