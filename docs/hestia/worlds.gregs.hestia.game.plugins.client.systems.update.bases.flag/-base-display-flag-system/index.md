---
title: BaseDisplayFlagSystem - hestia
layout: api
---

<div class='api-docs-breadcrumbs'><a href="../../index.html">hestia</a> / <a href="../index.html">worlds.gregs.hestia.game.plugins.client.systems.update.bases.flag</a> / <a href="./index.html">BaseDisplayFlagSystem</a></div>

# BaseDisplayFlagSystem

<div class="signature"><code><span class="keyword">abstract</span> <span class="keyword">class </span><span class="identifier">BaseDisplayFlagSystem</span>&nbsp;<span class="symbol">:</span>&nbsp;<span class="identifier">IteratingSystem</span></code></div>

### Constructors

<table class="api-docs-table">
<tbody>
<tr>
<td markdown="1">

<a href="-init-.html">&lt;init&gt;</a>


</td>
<td markdown="1">
<div class="signature"><code><span class="identifier">BaseDisplayFlagSystem</span><span class="symbol">(</span><span class="parameterName" id="worlds.gregs.hestia.game.plugins.client.systems.update.bases.flag.BaseDisplayFlagSystem$<init>(com.artemis.Aspect.Builder)/aspect">aspect</span><span class="symbol">:</span>&nbsp;<span class="identifier">Builder</span><span class="symbol">)</span></code></div>

</td>
</tr>
</tbody>
</table>

### Functions

<table class="api-docs-table">
<tbody>
<tr>
<td markdown="1">

<a href="add-check.html">addCheck</a>


</td>
<td markdown="1">
<div class="signature"><code><span class="keyword">fun </span><span class="identifier">addCheck</span><span class="symbol">(</span><br/>&nbsp;&nbsp;&nbsp;&nbsp;<span class="parameterName" id="worlds.gregs.hestia.game.plugins.client.systems.update.bases.flag.BaseDisplayFlagSystem$addCheck(worlds.gregs.hestia.game.update.DisplayFlag, kotlin.Function2((kotlin.Int, , kotlin.Boolean)))/flag">flag</span><span class="symbol">:</span>&nbsp;<a href="../../worlds.gregs.hestia.game.update/-display-flag/index.html"><span class="identifier">DisplayFlag</span></a><span class="symbol">?</span><span class="symbol">, </span><br/>&nbsp;&nbsp;&nbsp;&nbsp;<span class="parameterName" id="worlds.gregs.hestia.game.plugins.client.systems.update.bases.flag.BaseDisplayFlagSystem$addCheck(worlds.gregs.hestia.game.update.DisplayFlag, kotlin.Function2((kotlin.Int, , kotlin.Boolean)))/check">check</span><span class="symbol">:</span>&nbsp;<span class="symbol">(</span><a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html"><span class="identifier">Int</span></a><span class="symbol">,</span>&nbsp;<a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html"><span class="identifier">Int</span></a><span class="symbol">)</span>&nbsp;<span class="symbol">-&gt;</span>&nbsp;<a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html"><span class="identifier">Boolean</span></a><br/><span class="symbol">)</span><span class="symbol">: </span><a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html"><span class="identifier">Unit</span></a></code></div>

</td>
</tr>
<tr>
<td markdown="1">

<a href="check.html">check</a>


</td>
<td markdown="1">
<div class="signature"><code><span class="keyword">fun </span><span class="identifier">check</span><span class="symbol">(</span><br/>&nbsp;&nbsp;&nbsp;&nbsp;<span class="parameterName" id="worlds.gregs.hestia.game.plugins.client.systems.update.bases.flag.BaseDisplayFlagSystem$check(kotlin.Int, com.artemis.ComponentMapper((worlds.gregs.hestia.game.plugins.client.components.update.list.Entities)))/entityId">entityId</span><span class="symbol">:</span>&nbsp;<a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html"><span class="identifier">Int</span></a><span class="symbol">, </span><br/>&nbsp;&nbsp;&nbsp;&nbsp;<span class="parameterName" id="worlds.gregs.hestia.game.plugins.client.systems.update.bases.flag.BaseDisplayFlagSystem$check(kotlin.Int, com.artemis.ComponentMapper((worlds.gregs.hestia.game.plugins.client.components.update.list.Entities)))/mapper">mapper</span><span class="symbol">:</span>&nbsp;<span class="identifier">ComponentMapper</span><span class="symbol">&lt;</span><span class="keyword">out</span>&nbsp;<a href="../../worlds.gregs.hestia.game.plugins.client.components.update.list/-entities/index.html"><span class="identifier">Entities</span></a><span class="symbol">&gt;</span><br/><span class="symbol">)</span><span class="symbol">: </span><a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html"><span class="identifier">HashMap</span></a><span class="symbol">&lt;</span><a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html"><span class="identifier">Int</span></a><span class="symbol">,</span>&nbsp;<a href="../../worlds.gregs.hestia.game.update/-display-flag/index.html"><span class="identifier">DisplayFlag</span></a><span class="symbol">&gt;</span></code></div>

<div class="signature"><code><span class="keyword">fun </span><span class="identifier">check</span><span class="symbol">(</span><br/>&nbsp;&nbsp;&nbsp;&nbsp;<span class="parameterName" id="worlds.gregs.hestia.game.plugins.client.systems.update.bases.flag.BaseDisplayFlagSystem$check(kotlin.Int, kotlin.collections.List((kotlin.Int)))/entityId">entityId</span><span class="symbol">:</span>&nbsp;<a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html"><span class="identifier">Int</span></a><span class="symbol">, </span><br/>&nbsp;&nbsp;&nbsp;&nbsp;<span class="parameterName" id="worlds.gregs.hestia.game.plugins.client.systems.update.bases.flag.BaseDisplayFlagSystem$check(kotlin.Int, kotlin.collections.List((kotlin.Int)))/list">list</span><span class="symbol">:</span>&nbsp;<a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html"><span class="identifier">List</span></a><span class="symbol">&lt;</span><a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html"><span class="identifier">Int</span></a><span class="symbol">&gt;</span><br/><span class="symbol">)</span><span class="symbol">: </span><a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html"><span class="identifier">HashMap</span></a><span class="symbol">&lt;</span><a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html"><span class="identifier">Int</span></a><span class="symbol">,</span>&nbsp;<a href="../../worlds.gregs.hestia.game.update/-display-flag/index.html"><span class="identifier">DisplayFlag</span></a><span class="symbol">&gt;</span></code></div>

</td>
</tr>
<tr>
<td markdown="1">

<a href="initialize.html">initialize</a>


</td>
<td markdown="1">
<div class="signature"><code><span class="keyword">open</span> <span class="keyword">fun </span><span class="identifier">initialize</span><span class="symbol">(</span><span class="symbol">)</span><span class="symbol">: </span><a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html"><span class="identifier">Unit</span></a></code></div>

</td>
</tr>
</tbody>
</table>

### Inheritors

<table class="api-docs-table">
<tbody>
<tr>
<td markdown="1">

<a href="../../worlds.gregs.hestia.game.plugins.client.systems.update.stage/-global-display-flag-system/index.html">GlobalDisplayFlagSystem</a>


</td>
<td markdown="1">
<div class="signature"><code><span class="keyword">class </span><span class="identifier">GlobalDisplayFlagSystem</span>&nbsp;<span class="symbol">:</span>&nbsp;<a href="./index.html"><span class="identifier">BaseDisplayFlagSystem</span></a></code></div>

</td>
</tr>
<tr>
<td markdown="1">

<a href="../../worlds.gregs.hestia.game.plugins.client.systems.update.stage/-local-display-flag-system/index.html">LocalDisplayFlagSystem</a>


</td>
<td markdown="1">
<div class="signature"><code><span class="keyword">class </span><span class="identifier">LocalDisplayFlagSystem</span>&nbsp;<span class="symbol">:</span>&nbsp;<a href="./index.html"><span class="identifier">BaseDisplayFlagSystem</span></a></code></div>

</td>
</tr>
</tbody>
</table>
