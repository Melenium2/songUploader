<#import "common/defaultPage.ftl" as p />

<@p.page>
<style>
    <#include "style/starterPageContentStyle.css" />
  </style>

<div class="form-input">
  <form id="loaderForm" enctype="multipart/form-data">
    <span class="badge badge-pill badge-light form-control">Title here</span>
    <label class="btn btn-primary form-control" for="inpFile">
      <input type="file" name="filePath" eclass="form-control-file" id="inpFile" style="display:none" />
      Chose file for upload
    </label>
    <input type="text" class="form-control mt-1" disabled="disabled" placeholder="Song name" id="inpSongpath"  />
    <button type="submit" class="btn btn-primary form-control mt-2" id="btnUpload">Upload</button>
  </form>
    <div class="progress mt-2" id="blockProgress" style="display: none">
        <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" id="progressUpload">
        </div>
    </div>
    <div id="pictureBlock" style="display:none">
      <img src="" id="pictureBox"  class="rounded mx-auto d-block mt-2"/>
    </div>
  <input type="hidden" id="inpImg" />
  <input type="hidden" id="inpImagesUrl" />
  <input type="hidden" id="inpMusicUrl" />
  <input type="hidden" id="inpMusicLenght"
</div>

<div id="modalDialog" style="display: none">
  <button type="button" id="btnModal" data-toggle="modal" data-target="#modalWindow"></button>
</div>
<div class="modal fade" id="modalWindow" tabindex="-1" role="dialog" aria-labelledly="modalTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modalTitle">Upload success</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Your song upload completed!
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<div id="modalErrorDialog" style="display: none">
  <button type="button" id="btnErrorModal" data-toggle="modal" data-target="#modalErrorWindow"></button>
</div>
<div class="modal fade" id="modalErrorWindow" role="dialog" tabindex="-1" aria-labelledly="modalErrorTitle" aria-hidden=true>
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modalErrorTitle">Somthing wrong</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="close">
          <span aria-hidden=true>&times;</span>
        </button>
      </div>
      <div class="modal-body" id="errorMessage">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

  <script type="text/javascript">
    <#include "scripts/firebaseConfig.js" />
    <#include "scripts/newTrack.js" />
  </script>
</@p.page>