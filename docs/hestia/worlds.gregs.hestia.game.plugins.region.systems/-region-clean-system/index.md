---
title: RegionCleanSystem - hestia
layout: api
---

<div class='api-docs-breadcrumbs'><a href="../../index.html">hestia</a> / <a href="../index.html">worlds.gregs.hestia.game.plugins.region.systems</a> / <a href="./index.html">RegionCleanSystem</a></div>

# RegionCleanSystem

<div class="signature"><code><span class="identifier">@Wire</span><span class="symbol">(</span>false<span class="symbol">)</span> <span class="keyword">class </span><span class="identifier">RegionCleanSystem</span>&nbsp;<span class="symbol">:</span>&nbsp;<a href="../../worlds.gregs.hestia.game.api/-subscription-system/index.html"><span class="identifier">SubscriptionSystem</span></a></code></div>

Region Clean System
Unloads regions which has no prioritised entities in
Entities in this case refers to non-mob's (Players &amp; bots)

### Constructors

<table class="api-docs-table">
<tbody>
<tr>
<td markdown="1">

<a href="-init-.html">&lt;init&gt;</a>


</td>
<td markdown="1">
<div class="signature"><code><span class="identifier">RegionCleanSystem</span><span class="symbol">(</span><span class="symbol">)</span></code></div>

Region Clean System
Unloads regions which has no prioritised entities in
Entities in this case refers to non-mob's (Players &amp; bots)


</td>
</tr>
</tbody>
</table>

### Functions

<table class="api-docs-table">
<tbody>
<tr>
<td markdown="1">

<a href="removed.html">removed</a>


</td>
<td markdown="1">
<div class="signature"><code><span class="keyword">fun </span><span class="identifier">removed</span><span class="symbol">(</span><span class="parameterName" id="worlds.gregs.hestia.game.plugins.region.systems.RegionCleanSystem$removed(kotlin.Int)/entityId">entityId</span><span class="symbol">:</span>&nbsp;<a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html"><span class="identifier">Int</span></a><span class="symbol">)</span><span class="symbol">: </span><a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html"><span class="identifier">Unit</span></a></code></div>

</td>
</tr>
</tbody>
</table>

### Inherited Functions

<table class="api-docs-table">
<tbody>
<tr>
<td markdown="1">

<a href="../../worlds.gregs.hestia.game.api/-subscription-system/check-processing.html">checkProcessing</a>


</td>
<td markdown="1">
<div class="signature"><code><span class="keyword">open</span> <span class="keyword">fun </span><span class="identifier">checkProcessing</span><span class="symbol">(</span><span class="symbol">)</span><span class="symbol">: </span><a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html"><span class="identifier">Boolean</span></a></code></div>

</td>
</tr>
<tr>
<td markdown="1">

<a href="../../worlds.gregs.hestia.game.api/-subscription-system/process-system.html">processSystem</a>


</td>
<td markdown="1">
<div class="signature"><code><span class="keyword">open</span> <span class="keyword">fun </span><span class="identifier">processSystem</span><span class="symbol">(</span><span class="symbol">)</span><span class="symbol">: </span><a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html"><span class="identifier">Unit</span></a></code></div>

</td>
</tr>
</tbody>
</table>
