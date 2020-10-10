<html>
<link rel="stylesheet" href="/css/style.css">
  <title>Spanish by Hari</title>
  <div class="header" id="myHeader">
    <h2>
    <div class = "quarter">
    <a href = "/">Home</a>
    </div>
    <div id = "headie" class = "half">
      Live Translator
    </div>
    </h2>
  </div>
  <br>
  <br>
  <div class = "column left">
  <h4>
  s to save
  <br><hr>
  e to download
  </h4>
  </div>
  <div class = "column middle">
    ${value}
    <br>
  </div>
  <div id = "saver" class = "column right">
  <h4>
    <span>Saved Words:<span>
    <br><hr>
  </h4>
  </div>
  <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="js/FileSaver.js"></script>
  <script>
    // When the user scrolls the page, execute myFunction

    let savedWords = "Saved Words: <br><hr>"
    window.onscroll = function() {sticker()};

    // Get the navbar
    var navbar = document.getElementById("myHeader");

    // Get the offset position of the navbar
    var sticky = navbar.offsetTop;

    // Add the sticky class to the navbar when you reach its scroll position. Remove "sticky" when you leave the scroll position
    function sticker() {
    if (window.pageYOffset >= sticky) {
        navbar.classList.add("sticky")
    } else {
        navbar.classList.remove("sticky");
    }
    } 

  function getTranslation(obj){
          // Query and put results
      const postParameters = {
        word: obj.innerHTML
      };
      var text = window.getSelection().toString();
      if (text != ""){
        postParameters = {
        word: text
      };
      }

    $.post("/live", postParameters, response => {
        const jsonRes = JSON.parse(response);

        $("#headie").html(jsonRes.word);
    });
  }

    function getTranslationHighlight(){
          // Query and put results
      var text = window.getSelection().toString();
      if (text != ""){
        const postParameters = {
          word: text
      };
      $.post("/live", postParameters, response => {
          const jsonRes = JSON.parse(response);

          $("#headie").html(jsonRes.word);
      });
      }
  }

  document.onmouseup = document.onkeyup = document.onselectionchange = function() {
    getTranslationHighlight();
  };

  document.body.onkeyup = function(e){
    if(e.keyCode == 83){
        savedWords += $('#headie').text()
        savedWords += "<br><hr>"
        $("#saver span").html(savedWords);
    }
    if (e.keyCode == 69){
      let userInput = $("#saver span").html().replaceAll("<br><hr>", "\n");
      let file = new Blob([userInput], { type: "text/plain;charset=utf-8" });
      saveAs(file, "spanishbyhari"+Math.floor(Math.random() * 1000000)+".txt");
    }
  }
  </script>
</html>