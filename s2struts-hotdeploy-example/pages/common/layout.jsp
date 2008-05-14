<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <link href="../css/global.css" rel="stylesheet" type="text/css" id="globalStyle"></link>
  <script type="text/javascript" src="../js/execute.js" id="executeJs"></script>
  <title><tiles:getAsString name="title" /></title>
</head>

<body>
<table border="0" cellspacing="5" width="97%">
<tr>
  <td colspan="2">
    <tiles:insert attribute="header" />
  </td>
</tr>
<tr>
  <td width="200" valign="top">
    <tiles:insert attribute="menu" />
  </td>
  <td valign="top" align="left">
    <tiles:insert attribute="errors" />
    <tiles:insert attribute="body" />
  </td>
</tr>
<tr>
  <td colspan="2">
    <hr/>
  </td>
</tr>
<tr>
  <td colspan="2">
    <tiles:insert attribute="footer" />
  </td>
</tr>
</table>
</body>
</html>