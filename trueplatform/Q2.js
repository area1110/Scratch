function main (){

    let arr1 = [1, 2, 3, 4, 5, 6];
    let arr2 = [1, 2, 3, 4, 5];
    console.log(checkEquals(arr1, arr2));

}

function checkEquals(arr1, arr2){

    if(arr1.length !== arr2.length){
        return false
    }

    // Sort both arrays
    let sortedArr1 = arr1.slice().sort();
    let sortedArr2 = arr2.slice().sort();

    // If the elements are not equal, return false
    for(let i = 0; i < sortedArr1.length; i++){
        if(sortedArr1[i] !== sortedArr2[i]){
            return false;
        }
    }
    return true;
}

main();