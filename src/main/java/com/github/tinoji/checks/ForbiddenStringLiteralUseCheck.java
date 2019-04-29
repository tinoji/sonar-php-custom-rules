/*
 * SonarQube PHP Custom Rules Example
 * Copyright (C) 2016-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.github.tinoji.checks;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.Tree.Kind;
import org.sonar.plugins.php.api.tree.expression.LiteralTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

@Rule(
        key = ForbiddenStringLiteralUseCheck.KEY,
        priority = Priority.BLOCKER,
        name = "Forbidden string literal should not be used.",
        tags = {"custom"}
)
public class ForbiddenStringLiteralUseCheck extends PHPVisitorCheck {

    private static final String MESSAGE = "Remove the usage of this forbidden string literal.";
    public static final String KEY = "S1";

    @Override
    public void visitLiteral(LiteralTree tree) {
        List<String> forbiddenStrings = loadForbiddenList();
        String value = tree.value().replaceAll("\"", "");

        if (tree.is(Kind.REGULAR_STRING_LITERAL) && forbiddenStrings.contains(value)) {
            context().newIssue(this, tree, MESSAGE);
        }

        super.visitLiteral(tree);
    }

    private List<String> loadForbiddenList() {
        try {
            return Files.readAllLines(Paths.get("forbidden.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return Arrays.asList();
        }
    }
}