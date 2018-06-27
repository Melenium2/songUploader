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
</div>

  <script type="text/javascript">
    <#include "scripts/firebaseConfig.js" />
    <#include "scripts/newTrack.js" />
  </script>
</@p.page>