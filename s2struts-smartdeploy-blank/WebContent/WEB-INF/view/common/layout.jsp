<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
    <html:errors/>
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