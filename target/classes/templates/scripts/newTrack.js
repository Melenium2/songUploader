$(document).ready(function(){
    
  $("#loaderForm").submit(function(event){
    event.preventDefault();
    if(document.querySelector('#inpFile').files[0] == null)
    {
      $("#errorMessage").text("Chose audio file");
      $("#btnErrorModal").trigger('click');
    }
    else if(document.querySelector('#inpFile').files[0].type != 'audio/mp3')
    {
      uploadAuidio();
      uploadPicture();
      checkUpload();
    }
  });
  
  $("#inpFile").on('change', function(){
    getMetadataTag();
    getAudioDuration(document.querySelector('#inpFile').files[0]);
    alert(document.querySelector('#inpFile').files[0].type);
  });
});

function upload(file, path)
{
  var storRef = firebase.storage().ref();
  var metadata = {contentType: file.type};

  var imgOrSong = path.split("/");
  if (imgOrSong[0] == "Images"){
    console.log("uploading pictureBox");
    var uploadTask = storRef.child(path).putString(file.substring(23), 'base64');
  }else{
    console.log("uploaduing song");
    var uploadTask = storRef.child(path).put(file, metadata);
  }
  
  uploadTask.on('state_changed', function(snapshot){
    if (imgOrSong[0] == "Music"){
      var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
      $("#blockProgress").css('display', 'block');
      $("#progressUpload").text(progress.toFixed(1)+'%');
      $("#progressUpload").css('width', progress+'%').attr('aria-valuenow', progress);
    }
  }, function(error){
    console.log(error);
    
  }, function(){
    uploadTask.snapshot.ref.getDownloadURL().then(function(downloadUrl){
      console.log("#inp"+imgOrSong[0]+"Url" + downloadUrl);
      $("#inp"+imgOrSong[0]+"Url").val(downloadUrl);
    });
  });
}

function uploadAuidio()
{
  var file = document.querySelector('#inpFile').files[0];
  upload(file, "Music/" + $("#inpSongpath").prop('placeholder'));
}

function uploadPicture()
{
  upload($("#inpImg").val(), "Images/" + $("#inpSongpath").prop('placeholder'));
}

function getMetadataTag()
{
  var file = document.querySelector('#inpFile').files[0];
  jsmediatags.read(file, {
    onSuccess: function(tag){
      var tags = tag.tags;
      
      $("#inpSongpath").prop('placeholder', tags.artist + " - " + tags.title);
      var image = tags.picture;
      if(image)
      {
        var base64string = "";
        for(var i=0; i < image.data.length; i++)
        {
          base64string += String.fromCharCode(image.data[i]);
        }
        var base64 = "data:"+image.format+";base64,"+window.btoa(base64string);
        $("#pictureBox").attr('src', base64);
        $("#inpImg").val(base64);
        $("#pictureBlock").css('display', 'block');
      }
      else
      {
        console.log("no pic");
      }
    }
  });
}


function checkUpload()
{
  var timer = setInterval(function(){  
    var pictureUrl = $("#inpImagesUrl").val();
    var songUrl = $("#inpMusicUrl").val();
    if(pictureUrl != "" && songUrl != "")
    {
      setTimeout(function(){
        addToFirestore();
        clearInterval(timer);
      }, 500);
    }
  },5000);
}

function getAudioDuration(file)
{
  var audio = new Audio();
  var urlObject = URL.createObjectURL(file);
  audio.src = urlObject;

  $(audio).on('canplaythrough', function(e){
    var milliseconds = e.currentTarget.duration*1000;
    var len = milliseconds.toString().replace(/\.\S+/ig, "");  
    $("#inpMusicLenght").val(len);
  });
}

function addToFirestore()
{
  var songName = $("#inpSongpath").prop('placeholder').split(" - ");
  var json = {};
  
  json["songArtist"] = songName[0];
  json["songTitle"] = songName[1];  
  json["songDuration"] = $("#inpMusicLenght").val();
  json["songPath"] = $("#inpImagesUrl").val();
  json["songPicture"] = $("#inpMusicUrl").val();
  
  ajaxToServer(json).then(function(result){
    
      $.each(result, function(key, value){
        if (value === 'Success'){
          $("#btnModal").trigger('click');
        }
      });
      
  }).catch(function(error){
      $("#errorMessage").text(error);
      $("#btnErrorModal").trigger('click');
  });
}

function ajaxToServer(json)
{
  return new Promise(function(resolve, reject){
    var options = { type: 'POST', 
                    contentType: 'application/json', 
                    url: "/store",
                    data: JSON.stringify(json), 
                    dataType: 'json',
                    cache: false,
                    timeout: 60000  };
    $.ajax(options).done(resolve).fail(reject);
  });
}

















