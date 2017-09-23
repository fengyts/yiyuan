<#assign domain="${requestContextPath.contextPath}" />
<#assign imgPath="C:/Users/Public/Pictures/Sample Pictures" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>

	<title>Sortable. No jQuery.</title>

	<meta name="keywords" content="sortable, reorder, list, javascript, html5, drag and drop, dnd, rubaxa"/>
	<meta name="description" content="Sortable - is a minimalist JavaScript library for modern browsers and touch devices (No jQuery)."/>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<script src="${domain}/statics/plugin/jquery/jquery-1.9.1/jquery.min.js"></script>
	<link href='http://cdnjs.gtimg.com/cdnjs/libs/jquery//css?family=Roboto:300' rel='stylesheet' type='text/css'/>

	<style>
		html {
			background-image: -webkit-linear-gradient(bottom, #F4E2C9 20%, #F4D7C9 100%);
			background-image: -ms-linear-gradient(bottom, #F4E2C9 20%, #F4D7C9 100%);
			background-image: linear-gradient(to bottom, #F4E2C9 20%, #F4D7C9 100%);
		}

		html, body {
			margin: 0;
			padding: 0;
			position: relative;
			color: #464637;
			min-height: 100%;
			font-size: 20px;
			font-family: 'Roboto', sans-serif;
			font-weight: 300;
		}


		h1 {
			color: #FF3F00;
			font-size: 20px;
			font-family: 'Roboto', sans-serif;
			font-weight: 300;
			text-align: center;
		}


		ul {
			margin: 0;
			padding: 0;
			list-style: none;
		}

		.container {
			width: 80%;
			margin: auto;
			min-width: 1100px;
			max-width: 1300px;
			position: relative;
		}

		@media (min-width: 750px) and (max-width: 970px){
			.container {
				width: 100%;
				min-width: 750px;
			}
		}


		.sortable-ghost {
			opacity: .2;
		}


		img {
			border: 0;
			vertical-align: middle;
		}


		.logo {
			top: 55px;
			left: 30px;
			position: absolute;
		}


		.title {
			color: #fff;
			padding: 3px 10px;
			display: inline-block;
			position: relative;
			background-color: #FF7373;
			z-index: 1000;
		}
			.title_xl {
				padding: 3px 15px;
				font-size: 40px;
			}



		.tile {
			width: 22%;
			min-width: 245px;
			color: #FF7270;
			padding: 10px 30px;
			text-align: center;
			margin-top: 15px;
			margin-left: 5px;
			margin-right: 30px;
			background-color: #fff;
			display: inline-block;
			vertical-align: top;
		}
			.tile__name {
				cursor: move;
				padding-bottom: 10px;
				border-bottom: 1px solid #FF7373;
			}

			.tile__list {
				margin-top: 10px;
			}
				.tile__list:last-child {
					margin-right: 0;
					min-height: 80px;
				}

				.tile__list img {
					cursor: move;
					margin: 10px;
					border-radius: 100%;
				}



		.block {
			opacity: 1;
			position: absolute;
		}
			.block__list {
				padding: 20px 0;
				max-width: 360px;
				margin-top: -8px;
				margin-left: 5px;
				background-color: #fff;
			}
				.block__list li { cursor: move; }

			.block__list_words li {
				background-color: #fff;
				padding: 10px 40px;
			}
				.block__list_words .sortable-ghost {
					opacity: 0.4;
					background-color: #F4E2C9;
				}

				.block__list_words li:first-letter {
					text-transform: uppercase;
				}

			.block__list_tags {
				padding-left: 30px;
			}

			.block__list_tags:after {
				clear: both;
				content: '';
				display: block;
			}
				.block__list_tags li {
					color: #fff;
					float: left;
					margin: 8px 20px 10px 0;
					padding: 5px 10px;
					min-width: 10px;
					background-color: #5F9EDF;
					text-align: center;
				}
					.block__list_tags li:first-child:first-letter {
						text-transform: uppercase;
					}
	</style>
</head>
<body>


	<div class="container">
		<div style="padding: 80px 150px 0; height: 160px;">
			<a class="logo" href="https://github.com/RubaXa/Sortable"><img src="${imgPath}/yjx.png"/></a>
			<h1 data-force="40" data-force-y="2.5">The JavaScript library for modern browsers and touch devices. No&nbsp;jQuery.</h1>
		</div>
	</div>


	<div class="container" style="height: 520px">
		<div data-force="30" class="layer block" style="left: 14.5%; top: 0; width: 37%">
			<div class="layer title">List A</div>
			<ul id="foo" class="block__list block__list_words">
				<li>бегемот</li>
				<li>корм</li>
				<li>антон</li>
				<li>сало</li>
				<li>железосталь</li>
				<li>валик</li>
				<li>кровать</li>
				<li>краб</li>
			</ul>
		</div>

		<div data-force="18" class="layer block" style="left: 58%; top: 143px; width: 40%;">
			<div class="layer title">List B</div>
			<ul id="bar" class="block__list block__list_tags">
				<li>казнить</li>
				<li>,</li>
				<li>нельзя</li>
				<li>помиловать</li>
			</ul>
		</div>
	</div>


	<div class="container">
		<div id="multi" style="margin-left: 30px">
			<div><div data-force="5" class="layer title title_xl">Multi</div></div>

			<div class="layer tile" data-force="30">
				<div class="tile__name">Group A</div>
				<div class="tile__list">
					<img src="${imgPath}/tx1.jpg"/><!--
					--><img src="${imgPath}/tx1.jpg"/><!--
					--><img src="${imgPath}/tx1.jpg"/><!--
					--><img src="${imgPath}/tx1.jpg"/>
				</div>
			</div>

			<div class="layer tile" data-force="25">
				<div class="tile__name">Group B</div>
				<div class="tile__list">
					<img src="${imgPath}/tx1.jpg"/><!--
					--><img src="${imgPath}/tx1.jpg"/><!--
					--><img src="${imgPath}/tx1.jpg"/>
				</div>
			</div>

			<div class="layer tile" data-force="20">
				<div class="tile__name">Group C</div>
				<div class="tile__list">
					<img src="${domain}/statics/images/a.png"/><!--
					--><img src="${domain}/statics/images/delete.png"/>
				</div>
			</div>

		</div>
	</div>


	<div class="container" style="margin-top: 100px">
		<div style="margin-left: 30px">
			<div><div class="layer title title_xl">Code example</div></div>
			<pre data-force="100" class="layer javascript" style="margin-top: -8px; margin-left: 10px; width: 90%"><code>// Simple list
var list = document.getElementById("my-ui-list");
new Sortable(list); // That's all.


// Grouping
var foo = document.getElementById("foo");
new Sortable(foo, { group: "omega" });

var bar = document.getElementById("bar");
new Sortable(bar, { group: "omega" });


// Or
var container = document.getElementById("multi");
var sort = new Sortable(container, {
  handle: ".tile__title", // Restricts sort start click/touch to the specified element
  draggable: ".tile", // Specifies which items inside the element should be sortable
  onUpdate: function (evt/**Event*/){
     var item = evt.item; // the current dragged HTMLElement
  }
});

// ..
sort.destroy();
</code></pre>
		</div>
	</div>



	<script type="text/javascript" src="${domain}/statics/common/common-js/sortable.js"></script>

	<script>
		(function (){
			var console = window.console;

			if( !console.log ){
				console.log = function (){
					alert([].join.apply(arguments, ' '));
				};
			}


			window.x = new Sortable(foo, {
				group: "words",
				store: {
					get: function (sortable) {
						var order = localStorage.getItem(sortable.options.group);
						return order ? order.split('|') : [];
					},
					set: function (sortable) {
						var order = sortable.toArray();
						localStorage.setItem(sortable.options.group, order.join('|'));
					}
				},
				onAdd: function (evt){ console.log('onAdd.foo:', evt.item); },
				onUpdate: function (evt){ console.log('onUpdate.foo:', evt.item); },
				onRemove: function (evt){ console.log('onRemove.foo:', evt.item); },
				onStart:function(evt){ console.log('onStart.foo:',evt.item);},
				onEnd: function(evt){ console.log('onEnd.foo:', evt.item);}
			});


			new Sortable(bar, {
				group: "words",
				onAdd: function (evt){ console.log('onAdd.bar:', evt.item); },
				onUpdate: function (evt){ console.log('onUpdate.bar:', evt.item); },
				onRemove: function (evt){ console.log('onRemove.bar:', evt.item); },
				onStart:function(evt){ console.log('onStart.foo:', evt.item);},
				onEnd: function(evt){ console.log('onEnd.foo:', evt.item);}
			});


			new Sortable(multi, {
				draggable: '.tile',
				handle: '.tile__name'
			});


			[].forEach.call(multi.getElementsByClassName('tile__list'), function (el){
				new Sortable(el, { group: 'photo' });
			});
		})();



		// Background
		document.addEventListener( "DOMContentLoaded", function (){
			function setNoiseBackground(el, width, height, opacity){
				var canvas = document.createElement("canvas");
				var context = canvas.getContext("2d");

				canvas.width = width;
				canvas.height = height;

				for( var i = 0; i < width; i++ ){
					for( var j = 0; j < height; j++ ){
						var val = Math.floor(Math.random() * 255);
						context.fillStyle = "rgba(" + val + "," + val + "," + val + "," + opacity + ")";
						context.fillRect(i, j, 1, 1);
					}
				}

				el.style.background = "url(" + canvas.toDataURL("image/png") + ")";
			}

			setNoiseBackground(document.getElementsByTagName('body')[0], 50, 50, 0.02);
		}, false );
	</script>



	<!-- highlight.js -->
	<style>
		/* Tomorrow Theme */
		/* http://jmblog.github.com/color-themes-for-google-code-highlightjs */
		/* Original theme - https://github.com/chriskempson/tomorrow-theme */
		/* http://jmblog.github.com/color-themes-for-google-code-highlightjs */
		.tomorrow-comment, pre .comment, pre .title {
		  color: #8e908c;
		}

		.tomorrow-red, pre .variable, pre .attribute, pre .tag, pre .regexp, pre .ruby .constant, pre .xml .tag .title, pre .xml .pi, pre .xml .doctype, pre .html .doctype, pre .css .id, pre .css .class, pre .css .pseudo {
		  color: #c82829;
		}

		.tomorrow-orange, pre .number, pre .preprocessor, pre .built_in, pre .literal, pre .params, pre .constant {
		  color: #f5871f;
		}

		.tomorrow-yellow, pre .class, pre .ruby .class .title, pre .css .rules .attribute {
		  color: #eab700;
		}

		.tomorrow-green, pre .string, pre .value, pre .inheritance, pre .header, pre .ruby .symbol, pre .xml .cdata {
		  color: #718c00;
		}

		.tomorrow-aqua, pre .css .hexcolor {
		  color: #3e999f;
		}

		.tomorrow-blue, pre .function, pre .python .decorator, pre .python .title, pre .ruby .function .title, pre .ruby .title .keyword, pre .perl .sub, pre .javascript .title, pre .coffeescript .title {
		  color: #4271ae;
		}

		.tomorrow-purple, pre .keyword, pre .javascript .function {
		  color: #8959a8;
		}

		pre {
			border: 0;
			background-color: #fff;
		}

		pre code {
		  display: block;
		  color: #4d4d4c;
		  font-size: 15px;
		  font-family: Menlo, Monaco, Consolas, monospace;
		  line-height: 1.5;
		  padding: 30px;
		}
	</style>

	<#--<script src="http://libs.useso.com/js/highlight.js/7.5/highlight.min.js"></script>-->
	<script src="${domain}/statics/common/common-js/highlight.js"></script>
	<script>hljs.initHighlightingOnLoad();</script>

</body>
</html>
