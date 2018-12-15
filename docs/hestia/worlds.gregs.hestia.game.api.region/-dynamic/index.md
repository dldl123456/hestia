---
title: Dynamic - hestia
layout: api
---

<div class='api-docs-breadcrumbs'><a href="../../index.html">hestia</a> / <a href="../index.html">worlds.gregs.hestia.game.api.region</a> / <a href="./index.html">Dynamic</a></div>

# Dynamic

<div class="signature"><code><span class="keyword">abstract</span> <span class="keyword">class </span><span class="identifier">Dynamic</span>&nbsp;<span class="symbol">:</span>&nbsp;<span class="identifier">PassiveSystem</span></code></div>

### Constructors

<table class="api-docs-table">
<tbody>
<tr>
<td markdown="1">

<a href="-init-.html">&lt;init&gt;</a>


</td>
<td markdown="1">
<div class="signature"><code><span class="identifier">Dynamic</span><span class="symbol">(</span><span class="symbol">)</span></code></div>

</td>
</tr>
</tbody>
</table>

### Functions

<table class="api-docs-table">
<tbody>
<tr>
<td markdown="1">

<a href="create.html">create</a>


</td>
<td markdown="1">
<div class="signature"><code><span class="keyword">abstract</span> <span class="keyword">fun </span><span class="identifier">create</span><span class="symbol">(</span><span class="parameterName" id="worlds.gregs.hestia.game.api.region.Dynamic$create(kotlin.Int)/entityId">entityId</span><span class="symbol">:</span>&nbsp;<a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html"><span class="identifier">Int</span></a><span class="symbol">)</span><span class="symbol">: </span><a href="../../worlds.gregs.hestia.game.plugins.region.components/-dynamic-region/index.html"><span class="identifier">DynamicRegion</span></a></code></div>

</td>
</tr>
<tr>
<td markdown="1">

<a href="get.html">get</a>


</td>
<td markdown="1">
<div class="signature"><code><span class="keyword">abstract</span> <span class="keyword">fun </span><span class="identifier">get</span><span class="symbol">(</span><span class="parameterName" id="worlds.gregs.hestia.game.api.region.Dynamic$get(kotlin.Int)/entityId">entityId</span><span class="symbol">:</span>&nbsp;<a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html"><span class="identifier">Int</span></a><span class="symbol">)</span><span class="symbol">: </span><a href="../../worlds.gregs.hestia.game.plugins.region.components/-dynamic-region/index.html"><span class="identifier">DynamicRegion</span></a><span class="symbol">?</span></code></div>

</td>
</tr>
<tr>
<td markdown="1">

<a href="is-dynamic.html">isDynamic</a>


</td>
<td markdown="1">
<div class="signature"><code><span class="keyword">abstract</span> <span class="keyword">fun </span><span class="identifier">isDynamic</span><span class="symbol">(</span><span class="parameterName" id="worlds.gregs.hestia.game.api.region.Dynamic$isDynamic(kotlin.Int)/entityId">entityId</span><span class="symbol">:</span>&nbsp;<a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html"><span class="identifier">Int</span></a><span class="symbol">)</span><span class="symbol">: </span><a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html"><span class="identifier">Boolean</span></a></code></div>

</td>
</tr>
</tbody>
</table>

### Inheritors

<table class="api-docs-table">
<tbody>
<tr>
<td markdown="1">

<a href="../../worlds.gregs.hestia.game.plugins.region.systems/-dynamic-system/index.html">DynamicSystem</a>


</td>
<td markdown="1">
<div class="signature"><code><span class="keyword">class </span><span class="identifier">DynamicSystem</span>&nbsp;<span class="symbol">:</span>&nbsp;<a href="./index.html"><span class="identifier">Dynamic</span></a></code></div>

</td>
</tr>
</tbody>
</table>
