/**
 * Pagination jQuery code
 */

(function ($)
{
	//Function start
	$.fn.jPaginate=function(options)
	{
		//Default settings
		var defaults=
		{
				'max': 5,
				'page': 1,
				'links':'text'
		};
		var parameters=$.extend(defaults, options);
		//Check given values
		parameters.max=Math.max(1,parameters.max);
		parameters.page=Math.max(1,parameters.page);
		
		return this.each(function()
		{
			var table=$(this);
			if (table.is('table'))
				{
				var container=table.find('tbody');
				if(container.length==0)
					{
						container=table;	
					}
				var nbr_pages=0;
				var colspan=0;
				container.find('tr').each(function(i)
						{
							var row=$(this);
							if (i%parameters.max==0)
								{
									nbr_pages++;
								}
							row.addClass('jPaginate-row-'+nbr_pages);
							
							if(nbr_pages!=parameters.page)
								{
									row.hide();
								}
							if(i==0)
								{
									row.find('td,th').each(function()
											{
												colspan+=$(this).attr('colspan')!=undefined?1+parseInt($(this).attr('colspan'),10):1;
											});
								}
						});
				//Placed at the table footer
				var tfoot=table.find('tfoot');
				if(tfoot.length==0)
					{
						tfoot=$('<tfoot></tfoot>');
						tfoot.appendTo(table);
					}
				var th=$('<th class="sorttable_nosort footer" colspan="'+colspan+'"></th>');
				$('<tr class="jPaginate-links-row sorttable_nosort"></tr>').append(th).appendTo(tfoot);
			
			if(nbr_pages>1)
				{
				function showPage(num){
					container.find('tr').hide();
					container.find('.jPaginate-row-'+num).css('display','table-row');
				}
					
					var linksHTML='';
					switch(parameters.links)
					{
					
					//Drop down menu page select
					case 'select':
						for(var i=1; i<=nbr_pages; i++)
							{
								linksHTML+='<option value="'+i+'"'+'class="jPaginate-link-option'+(i==parameters.page?' jPaginate-link-option-selected" selected="selected':'')+'">'+i+'</option>';
							}
						th.html('<b>Select page:</b> '+'<select class="jPaginate-link-select">'+linksHTML+'</select>');
						th.find('select').change(function()
								{
									var select=$(this);
									select.find('.jPaginate-link-option').removeClass('jPaginate-link-option-selected');
									select.find('.jPaginate-link-option[value='+select.val()+']').addClass('jPaginate-link-option-selected');
									showPage(select.val());
								});
					break;
					
					//First, Last, Next and Previous buttons with input page in the middle of the next and previous buttons
					case 'buttons':
						
						th.html('<input class="jPaginate-link-button-first" type="button" value="&laquo; First">'+
							    '<input class="jPaginate-link-button-previous" type="button" value="&lt; Prev">'+
							    '<input class="jPaginate-link-input" type="text" value="'+parameters.page+'">'+
							    '<input class="jPaginate-link-button-next" type="button" value="Next &gt;">'+
							    '<input class="jPaginate-link-button-last" type="button" value="Last &raquo;">');

							    var input=th.find('.jPaginate-link-input');
							    
							    input.change(function()
							    	    {
							    	        var $this=$(this);
							    	        var val=isNaN($this.val())?1:$this.val();
							    	        $this.val(Math.max(1,Math.min(nbr_pages,val)));
							    	        showPage($this.val());
							    	    });
							    
							    th.find('.jPaginate-link-button-first').click(function()
							    	    {
							    	        input.val(1).change();
							    	    });

							    	    th.find('.jPaginate-link-button-last').click(function()
							    	    {
							    	        input.val(nbr_pages).change();
							    	    });

							    	    th.find('.jPaginate-link-button-previous').click(function()
							    	    {
							    	        input.val(parseInt(input.val(),10)-1).change();
							    	    });

							    	    th.find('.jPaginate-link-button-next').click(function()
							    	    {
							    	        input.val(parseInt(input.val(),10)+1).change();
							    	    });
						
					break;
					
					//First, Last, Next and Previous buttons with a drop down menu in the middle of the next and previous buttons 
					case "selectButtons":
					
						for(var i=1; i<=nbr_pages; i++)
						{
							linksHTML+='<option value="'+i+'"'+'class="jPaginate-link-option'+(i==parameters.page?' jPaginate-link-option-selected" selected="selected':'')+'">'+i+'</option>';
						}
						
						th.html('<input class="jPaginate-link-button-first" type="button" value="&laquo; First">'+
							    '<input class="jPaginate-link-button-previous" type="button" value="&lt; Prev">'+
							    '<strong>Page: </strong>'+'<select class="jPaginate-link-select">'+linksHTML+'</select>' +
							    '<input class="jPaginate-link-button-next" type="button" value="Next &gt;">'+
							    '<input class="jPaginate-link-button-last" type="button" value="Last &raquo;">');
					
								var select=th.find('.jPaginate-link-select');
							    select.change(function()
							    	    {
							    		var $this=$(this);
							    		var val=isNaN($this.val())?1:$this.val();
							    		$this.val(Math.max(1,Math.min(nbr_pages,val)));
							    		$this.find('.jPaginate-link-option').removeClass('jPaginate-link-option-selected');
										$this.find('.jPaginate-link-option[value='+$this.val()+']').addClass('jPaginate-link-option-selected');
							    		showPage($this.val());
							    	    });
							    
							    		th.find('.jPaginate-link-button-first').click(function()
							    	    {
							    	        select.val(1).change();
							    	    });

							    	    th.find('.jPaginate-link-button-last').click(function()
							    	    {
							    	        select.val(nbr_pages).change();
							    	    });

							    	    th.find('.jPaginate-link-button-previous').click(function()
							    	    {
							    	        select.val(parseInt(select.val(),10)-1).change();
							    	    });

							    	    th.find('.jPaginate-link-button-next').click(function()
							    	    {
							    	        select.val(parseInt(select.val(),10)+1).change();
							    	    });
						break;
					
					default:
					for (var i=1; i<=nbr_pages; i++)
						{
						linksHTML+='<a class="jPaginate-link-text'+(i==parameters.page?' jPaginate-link-text-active':'')+'" href="#">'+i+'</a> ';
						}
					
					th.html('Page: '+linksHTML);
					
					$('.jPaginate-link-text').click(function (e)
							{
								e.preventDefault();
								th.find('a').removeClass('jPaginate-link-text-active');
								$(this).addClass('jPaginate-link-text-active');
								showPage($(this).text());
							});
					break;
					}
				}
			}
		});
		/*	var newParamMax = [5, 10, 15, 20, 50, 100, 1000];
		var arrayLength = newParamMax.length;
	  
	 	for(var s = 0; s < arrayLength; s++)
		{
		pageHTML+='<option value="'+s+'"'+'class="jPaginate-page-option'+(s==newParamMax[s]?' jPaginate-page-option-selected" selected="selected':'')+'">'+s+'</option>';
		}
		th.html('&nbsp;<b>Display</b> '+'<select class="jPaginate-page-select">'+pageHTML+'</select> rows');*/
	};
})(jQuery);
		