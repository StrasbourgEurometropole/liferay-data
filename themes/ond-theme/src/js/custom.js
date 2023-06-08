// Function used to shorten a string disabled
function add3Dots(string, limit)
{
	var dots = "..."; 

	if (string.length > limit) 
	{ 
		string = string.substring(0, limit) + dots;
	}
	return string;
}
