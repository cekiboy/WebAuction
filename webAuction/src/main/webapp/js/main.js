$(document).ready(function() {
	//align main menu items
	var menu = new TMainMenu('mainMenu');
	menu.alignMenuItems();
	$('input[type="text"]:first').focus();
	
	
	//clear input field of auction bid value
	$('#bidValue').val("");
	if($('#startPrice').val()==0){
		$('#startPrice').val("");
	}
	if($('#buyOutPrice').val()==0){
		$('#buyOutPrice').val("");
	}
	

	
	//adds one file component on page load
	addBrowse();
    //add more file components if Add is clicked
    $('#addFile').click(function() {
        addBrowse();
    });
   
    
    //number of auctions
    var listSize = $('#listSize').val();
    for(var index = 0; index < listSize; index++) {
    	
	 // set the date we're counting down to
    	var auctionEndTime = $("#endTime"+index).val();
	    var target_date = new Date(auctionEndTime);
	    
	    var something = "dynamicVariableName";
	    eval("var " + something + " = countdown" + index);
	    
	    setTime(target_date, dynamicVariableName);
	
	    }
});


//adds another file browse form
function addBrowse() {
	var fileIndex = $('#fileTable tr').children().length ;
    $('#fileTable').append(
            '<tr><td>'+
            '   <input type="file" path="files['+ fileIndex +']" name="files['+ fileIndex +']" accept="image/*"/>'+
            '</td></tr>');
}

function setTime (target_date, element){
	//variables for time units
	var days, hours, minutes, seconds;
	
	setInterval (function() {
	    
	    // find the amount of "seconds" between now and target
	    var current_date = new Date().getTime();
	    var seconds_left = (target_date - current_date) / 1000;
	 
	    // do some time calculations
	    days = parseInt(seconds_left / 86400);
	    seconds_left = seconds_left % 86400;
	     
	    hours = parseInt(seconds_left / 3600);
	    seconds_left = seconds_left % 3600;
	     
	    minutes = parseInt(seconds_left / 60);
	    seconds = parseInt(seconds_left % 60);
	     
	    // format countdown string + set tag value
	    element.innerHTML = days + "d, " + hours + "h, "
	    + minutes + "m, " + seconds + "s";  
	 
	}, 1000);
	}


//simple images browser
function browseThrough(pictureId) {
    $("#gallery td").hide();
    $("td#"+pictureId).show();
    
    $("#galleryLinks td").show();
    $("td#link"+pictureId).hide();
}

function TMainMenu(unorderedListElement) {
	if (!unorderedListElement) return;
	if (typeof unorderedListElement === 'object' && typeof unorderedListElement.nodeName != 'undefined' && unorderedListElement.nodeName === 'ul') {
		this.root = $(unorderedListElement);
	} else if (typeof unorderedListElement === 'string') {
		this.root = $('#' + unorderedListElement);
	} else return;
}
TMainMenu.prototype.alignMenuItems = function() {
	if (typeof this.root === 'undefined') return;
	var menuItems = $('li', this.root);
	var idealWidth = Math.floor(this.root.outerWidth() / menuItems.length) - 18;
	menuItems.each(function() {
		var margin = Math.floor((idealWidth - $(this).outerWidth()) / 2) - 2;
		$(this).css({
			'width': idealWidth + 'px',
			'margin-right': '8px',
			'margin-right': '8px'
		});
	});
}
