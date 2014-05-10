<html>
<head>
    <title>Facebook Profile</title>
    <meta name='layout' content='bootstrap'/>
</head>

<body>


<div class="container">
  <div class="row">
    <div class="span9">
      <div class="form-container">
        <g:render template="/springsocial/facebook/facebookMenu" model="['active':'index']"/>

        <h3>Your Facebook Profile</h3>
        <figure>
          <img class="Photo" src="${createLink(controller:'springSocialFacebook', action:'profilePhoto')}" />
          <figcaption>Hello, ${profile.firstName}</figcaption>
        </figure>

        <dl class="dl-horizontal">
          <dt>Facebook ID:</dt>
          <dd>${profile.id}</dd>
          <dt>Name:</dt>
          <dd>${profile.name}</dd>
          <dt>Email:</dt>
          <dd>${profile.email}</dd>
        </dl>

        <g:form method="DELETE" mapping="springSocialConnect" params="[providerId:'facebook']">
            <button class="btn btn-lg btn-danger">Disconnect from Facebook</button>
        </g:form>

      </div>
    </div>
  </div>
</div>


</body>
</html>
